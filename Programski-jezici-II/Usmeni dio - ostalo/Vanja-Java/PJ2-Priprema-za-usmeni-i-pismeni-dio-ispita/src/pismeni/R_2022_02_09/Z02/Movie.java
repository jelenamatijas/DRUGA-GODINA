package pismeni.R_2022_02_09.Z02;

public class Movie {
    public int movie_id;
    public String title;
    public int budget;
    public String homepage;
    public String overview;
    public double popularity;
    public String release_date; // YYYY-MM-DD
    public int revenue;
    public int runtime;
    public String movie_status;
    public String tagline;
    public double vote_average;
    public int vote_count;

    public Movie(int movie_id, String title, int budget, String homepage, String overview, double popularity,
                 String release_date, int revenue, int runtime, String movie_status, String tagline, double vote_average, int vote_count) {
        this.movie_id = movie_id;
        this.title = title;
        this.budget = budget;
        this.homepage = homepage;
        this.overview = overview;
        this.popularity = popularity;
        this.release_date = release_date;
        this.revenue = revenue;
        this.runtime = runtime;
        this.movie_status = movie_status;
        this.tagline = tagline;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    @Override
    public String toString() {
        return movie_id + " "
                + title + " " +
                budget + " " +
                homepage + " " +
                overview + " " + " " +
                popularity + " " +
                release_date + " " +
                revenue + " " +
                runtime + " " +
                movie_status + " " +
                tagline + " " +
                vote_average + " " +
                vote_count;
    }
}