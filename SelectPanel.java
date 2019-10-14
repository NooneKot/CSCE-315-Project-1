import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class SelectPanel extends JPanel {
    // title basics
    private ColumnBox title_type;
    private ColumnBox primary_title;
    private ColumnBox original_title;
    private ColumnBox is_adult;
    private ColumnBox start_year;
    private ColumnBox end_year;
    private ColumnBox runtime;
    private ColumnBox genres;

    //names
    private  ColumnBox primary_name;
    private  ColumnBox birth_year;
    private  ColumnBox death_year;
    private  ColumnBox primary_profession;
    private  ColumnBox known_for_titles;

    //principals
    private  ColumnBox catagory;
    private  ColumnBox job;
    private ColumnBox characters;

    //Akas
    private ColumnBox title;
    private ColumnBox region;
    private ColumnBox language;
    private ColumnBox types;
    private ColumnBox attributes;
    private ColumnBox is_original_title;

    //crew
    private ColumnBox diretors;
    private ColumnBox writers;

    //ratings
    private ColumnBox avg_ratings;
    private ColumnBox num_votes;

    //episode
    private ColumnBox season_number;
    private ColumnBox episode_number;

    public SelectPanel() {
        //title basics
        title_type = new ColumnBox("Title Type");
        primary_title = new ColumnBox("Primary Title");
        original_title = new ColumnBox("Original Title");
        is_adult = new ColumnBox("Is Adult");
        start_year = new ColumnBox("Start Year");
        end_year = new ColumnBox("End Year");
        runtime = new ColumnBox("Runtime");
        genres = new ColumnBox("Genres");

        //names
        primary_name = new ColumnBox("Primary Name");
        birth_year = new ColumnBox("Birth Year");
        death_year = new ColumnBox("Death Year");
        primary_profession = new ColumnBox("Primary Profession");
        known_for_titles = new ColumnBox("Known for Titles");

        //principls
        catagory = new ColumnBox("Catagory");
        job = new ColumnBox("Job");
        characters = new ColumnBox("Characters");

        //Akas
        title = new ColumnBox("Title");
        region = new ColumnBox("Region");
        language = new ColumnBox("Language");
        types = new ColumnBox("Types");
        attributes = new ColumnBox("Attributes");
        is_original_title = new ColumnBox("Is Original Title");

        //crew
        diretors = new ColumnBox("Directors");
        writers = new ColumnBox("Writers");

        //ratings
        avg_ratings = new ColumnBox("Average Rating");
        num_votes = new ColumnBox("Number of Votes");

        //episode
        season_number = new ColumnBox("Season Number");
        episode_number = new ColumnBox("Episode Number");

        setLayout(new GridLayout(14, 2));

        //title basics
        add(title_type);
        add(primary_title);
        add(original_title);
        add(is_adult);
        add(start_year);
        add(end_year);
        add(runtime);
        add(genres);

        //names
        add(primary_name);
        add(birth_year);
        add(death_year);
        add(primary_profession);
        add(known_for_titles);

        //principles
        add(catagory);
        add(job);
        add(characters);

        //Akas
        add(title);
        add(region);
        add(language);
        add(types);
        add(attributes);
        add(is_original_title);

        //crew
        add(diretors);
        add(writers);

        //ratings
        add(avg_ratings);
        add(num_votes);

        //episode
        add(season_number);
        add(episode_number);

    }


    public ArrayList<String> getSearch() {
        ArrayList<String> Text = new ArrayList<String>(28);
        Text.add(0, title_type.getString());
        Text.add(1, primary_title.getString());
        Text.add(2, original_title.getString());
        Text.add(3, is_adult.getString());
        Text.add(4, start_year.getString());
        Text.add(5, end_year.getString());
        Text.add(6, runtime.getString());
        Text.add(7, genres.getString());
        Text.add(8, primary_name.getString());
        Text.add(9, birth_year.getString());
        Text.add(10, death_year.getString());
        Text.add(11, primary_profession.getString());
        Text.add(12, known_for_titles.getString());
        Text.add(13, catagory.getString());
        Text.add(14, job.getString());
        Text.add(15, characters.getString());
        Text.add(16, title.getString());
        Text.add(17, region.getString());
        Text.add(18, language.getString());
        Text.add(19, types.getString());
        Text.add(20, attributes.getString());
        Text.add(21, is_original_title.getString());
        Text.add(22, diretors.getString());
        Text.add(23, writers.getString());
        Text.add(24, avg_ratings.getString());
        Text.add(25, num_votes.getString());
        Text.add(26, season_number.getString());
        Text.add(27, episode_number.getString());

        return Text;
    }

    public ArrayList<Boolean> getF() {
        ArrayList<Boolean> Filter = new ArrayList<Boolean>(28);
        Filter.add(0, title_type.getFilter());
        Filter.add(1, primary_title.getFilter());
        Filter.add(2, original_title.getFilter());
        Filter.add(3, is_adult.getFilter());
        Filter.add(4, start_year.getFilter());
        Filter.add(5, end_year.getFilter());
        Filter.add(6, runtime.getFilter());
        Filter.add(7, genres.getFilter());
        Filter.add(8, primary_name.getFilter());
        Filter.add(9, birth_year.getFilter());
        Filter.add(10, death_year.getFilter());
        Filter.add(11, primary_profession.getFilter());
        Filter.add(12, known_for_titles.getFilter());
        Filter.add(13, catagory.getFilter());
        Filter.add(14, job.getFilter());
        Filter.add(15, characters.getFilter());
        Filter.add(16, title.getFilter());
        Filter.add(17, region.getFilter());
        Filter.add(18, language.getFilter());
        Filter.add(19, types.getFilter());
        Filter.add(20, attributes.getFilter());
        Filter.add(21, is_original_title.getFilter());
        Filter.add(22, diretors.getFilter());
        Filter.add(23, writers.getFilter());
        Filter.add(24, avg_ratings.getFilter());
        Filter.add(25, num_votes.getFilter());
        Filter.add(26, season_number.getFilter());
        Filter.add(27, episode_number.getFilter());

        return Filter;
    }

    public ArrayList<Boolean> getD() {
        ArrayList<Boolean> Display = new ArrayList<Boolean>(28);
        Display.add(0, title_type.getDisplay());
        Display.add(1, primary_title.getDisplay());
        Display.add(2, original_title.getDisplay());
        Display.add(3, is_adult.getDisplay());
        Display.add(4, start_year.getDisplay());
        Display.add(5, end_year.getDisplay());
        Display.add(6, runtime.getDisplay());
        Display.add(7, genres.getDisplay());
        Display.add(8, primary_name.getDisplay());
        Display.add(9, birth_year.getDisplay());
        Display.add(10, death_year.getDisplay());
        Display.add(11, primary_profession.getDisplay());
        Display.add(12, known_for_titles.getDisplay());
        Display.add(13, catagory.getDisplay());
        Display.add(14, job.getDisplay());
        Display.add(15, characters.getDisplay());
        Display.add(16, title.getDisplay());
        Display.add(17, region.getDisplay());
        Display.add(18, language.getDisplay());
        Display.add(19, types.getDisplay());
        Display.add(20, attributes.getDisplay());
        Display.add(21, is_original_title.getDisplay());
        Display.add(22, diretors.getDisplay());
        Display.add(23, writers.getDisplay());
        Display.add(24, avg_ratings.getDisplay());
        Display.add(25, num_votes.getDisplay());
        Display.add(26, season_number.getDisplay());
        Display.add(27, episode_number.getDisplay());

        return Display;

    }
}
