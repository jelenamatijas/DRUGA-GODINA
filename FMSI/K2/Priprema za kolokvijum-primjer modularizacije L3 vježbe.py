import numpy as np
import pandas as pd
import seaborn as sns
from sklearn.base import BaseEstimator
from sklearn.ensemble import AdaBoostRegressor
from sklearn.impute import SimpleImputer
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import LabelEncoder, OneHotEncoder, StandardScaler
from sklearn.tree import DecisionTreeRegressor


def load_dataset(dataset_name: str) -> pd.DataFrame:
    df = sns.load_dataset(dataset_name)
    return df


def get_label_encoder_columns() -> list[str]:
    return ["smoker"]


def get_one_hot_encoder_columns() -> list[str]:
    return ["sex", "day", "time"]


def label_encode(df: pd.DataFrame, columns: list[str]) -> tuple[pd.DataFrame, dict[str, LabelEncoder]]:
    df_encoded = df.copy()

    label_encoders = {}

    for column in columns:
        le = LabelEncoder()
        le.fit(df[column])
        df_encoded[column] = le.fit_transform(df[column])
        label_encoders[column] = le

    return df_encoded, label_encoders


def one_hot_encode(df: pd.DataFrame, columns: list[str]) -> tuple[pd.DataFrame, OneHotEncoder]:
    ohe = OneHotEncoder(sparse_output=False, handle_unknown="ignore")
    encoded_data = ohe.fit_transform(df[columns])
    feature_names = ohe.get_feature_names_out(columns)
    df_ohe = pd.DataFrame(encoded_data, columns=feature_names, index=df.index)
    df = df.drop(columns=columns).join(df_ohe)
    return df, ohe


def scale_df(df: pd.DataFrame) -> tuple[pd.DataFrame, StandardScaler]:
    scaler = StandardScaler()
    scale_cols = ["total_bill"]
    df[scale_cols] = scaler.fit_transform(df[scale_cols])
    return df, scaler


def generate_datasets(df: pd.DataFrame, random_state: int) -> tuple[np.ndarray, np.ndarray, np.ndarray, np.ndarray, np.ndarray, np.ndarray]:
    X = df.drop(columns=["tip"]).to_numpy()
    y = df["tip"].to_numpy()
    X_train, X_test, y_train, y_test = train_test_split(
        X, y, test_size=0.2, random_state=random_state
    )
    print()
    df.head()
    X_train, X_val, y_train, y_val = train_test_split(
        X_train, y_train, test_size=0.2, random_state=random_state
    )
    return X_train, X_val, X_test, y_train, y_val, y_test  # type: ignore


def get_model(max_tree_depth: int, num_tree_leaves: int, n_estimators: int, ada_lr: float) -> BaseEstimator:
    tree = DecisionTreeRegressor(
        max_depth=max_tree_depth, min_samples_leaf=num_tree_leaves
    )
    model = AdaBoostRegressor(tree, n_estimators=n_estimators, learning_rate=ada_lr)

    return model


def train_model(model: BaseEstimator, X_train: np.ndarray, y_train: np.ndarray):
    model.fit(X_train, y_train)  # type: ignore
    return model


def impute_missing_values(df: pd.DataFrame) -> tuple[pd.DataFrame, SimpleImputer]:

    imp = SimpleImputer(strategy="mean", fill_value="unknown")

    impute_cols = ["age"]
    df[impute_cols] = imp.fit_transform(df[impute_cols])
    return df, imp


# Izbrisati kolone koji imaju stepen korelacije 1.0
def remove_simillar_columns(df: pd.DataFrame) -> pd.DataFrame:
    raise NotImplementedError()


def test_model(
    model: BaseEstimator, X_test: np.ndarray, y_test: np.ndarray
) -> tuple[float, float]:
    raise NotImplementedError()
