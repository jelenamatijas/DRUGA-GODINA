def f(x):
    if x < 51:
        return 5
    jedn = x%10
    x = x // 10
    des = x%10
    x = x // 10
    if x:
        return 10
    elif jedn and des:
        return des+1
    else:
        return des
