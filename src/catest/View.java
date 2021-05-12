/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catest;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author jonpaulcarlo
 */
public class View extends JFrame {
    
    
    
    JFrame main;
    JFrame welcome;
    JFrame faq;
    JPanel movieSection;
    JTextField searchBar;
    JComboBox sort;
    CardLayout movieSectionL;
    
    JPanel moviesHome;
    int countMovies; //int for the search result, will change name later
    int countResult; //int for the total number of movies on the database
    int countIndex;
    String headerLabel; //shows what the user searched/what the movie panel is currently displaying
    
    JPanel movieHomeP;
    JPanel p1;
    Font txt;
    ScrollPane sp;
    
    Controller controller;
    Model model;
    
    public View(Controller controller)
    {
        this.controller = controller;
        // We encapsulated the building process of the window
        welcome();
        
    }

    
    public void welcome(){
        //welcome page
        welcome = new JFrame();
            welcome.setVisible(true);
            welcome.setSize(1600, 900);
            welcome.setTitle("Xtra Vision");
            BorderLayout welcomeLayout = new BorderLayout();
            welcome.setLayout(welcomeLayout);
        //header     
        Font tFont = new Font("Arial Black",Font.BOLD,50);
        Font tFont1 = new Font("Arial Black",Font.BOLD,20);
        
        JPanel header = new JPanel();
        JLabel xtra = new JLabel("Welcome to");
            xtra.setFont(tFont);
            xtra.setForeground(new Color(255, 228, 0));
            
            welcome.add(header, BorderLayout.PAGE_START);
            
        ImageIcon logoxtra = new ImageIcon(getClass().getResource("images/logoxtravision.png"));
            JLabel logo = new JLabel(logoxtra);
            GridLayout head = new GridLayout(2,1);
                header.setLayout(head);
                header.setBackground(new Color(160,0,0));//personalized colors
            header.add(xtra);
            header.add(logo);
            
        //center    
        JButton rent = new JButton("Rent a movie");
        rent.addActionListener(controller);
        rent.setActionCommand("rent");
        JButton retur = new JButton("Return a movie");
            
            JPanel rr = new JPanel();
                welcome.add(rr, BorderLayout.CENTER);
                rr.setBackground(new Color(160,0,0));
            GridLayout rentreturn = new GridLayout(1,2);
                rr.setLayout(rentreturn);
            rr.add(rent);
            rr.add(retur);
            
        //footer
        JPanel footer = new JPanel();
            welcome.add(footer, BorderLayout.PAGE_END);
            footer.setBackground(new Color(160,0,0));
        JLabel txtfooter = new JLabel("Xtra easy and Xtra fast way to rent a movie!");
            txtfooter.setFont(tFont1);
        footer.add(txtfooter);
            txtfooter.setForeground(new Color(255, 228, 0));        
            
        welcome.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        welcome.validate();
        welcome.repaint();
    }
    
    public void main() {
        //main frame (will change only the panels
        main = new JFrame();
            main.setVisible(true);
            main.setSize(1600, 900);
            main.setTitle("Xtra Vision");
            BorderLayout mainLayout = new BorderLayout();
            main.setLayout(mainLayout);
            
        txt = new Font("Arial Black", Font.PLAIN,15);
        main.add(searchBar(), BorderLayout.PAGE_START);   
        main.add(categories(), BorderLayout.LINE_START);  
        movieSection = new JPanel();
//        movieSection.setLayout(new BoxLayout(movieSection, BoxLayout.Y_AXIS)); ///////////////////////////
//        main.add(movieSection,BorderLayout.CENTER);
//        movieSection.add(moviePanel());

        movieSectionL = new CardLayout(); //cardlayout manager
        movieSection = new JPanel();
        movieSection.setLayout(movieSectionL);
        main.add(movieSection,BorderLayout.CENTER);
        movieSection.add(moviePanel(),"moviesHome");   //moviesHome is the identifier
//        movieSection.add(movieSearchPanel(),"searchResult");
//        
//        movieSectionL.show(movieSection, "moviesHome"); //sets the default
        
        
//        main.add(movieSection(),BorderLayout.CENTER);
                
        
        CardLayout cardL = new CardLayout();
        JPanel cardContainer;
        String buttonTest = "Panel with Buttons"; //identifier
        String textTest = "Panel with TextField";
        
        JPanel card1 = new JPanel();
        JPanel card2 = new JPanel();
        
        cardContainer = new JPanel();
        cardContainer.setLayout(cardL);  
        cardContainer.add(card1, buttonTest);  //(element or panel to add, identifier)
        cardContainer.add(card2,textTest);
        cardL.show(cardContainer, buttonTest); 
        
        //if a button is clicked tell controller that the CardLayout manager should change what panel to show (container, identifier)
        
        
        
        main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        main.validate();
        main.repaint();       
    }
    
    
    public JPanel searchBar(){
    
        JPanel search = new JPanel();

            search.setBackground(new Color(160,0,0));
            GridLayout searchGrid = new GridLayout(1,4);
            search.setLayout(searchGrid);
            //image    
            ImageIcon logoxtra = new ImageIcon(getClass().getResource("images/logoxtravision.png"));        
                logoxtra.setImage(logoxtra.getImage().getScaledInstance(150, 45, 100));    
                JLabel logo = new JLabel(logoxtra);
                    search.add(logo);

                searchBar = new JTextField();
                    search.add(searchBar);   
                JButton searchButton = new JButton("Search");
                    search.add(searchButton);
                    searchButton.addActionListener(controller);
                    searchButton.setActionCommand("search");
                JButton cart = new JButton("My Cart");
                    search.add(cart);
                    cart.addActionListener(controller);
                    cart.setActionCommand("cart");

                
        return search;        
    }
    
    
    
    public JPanel categories(){
        JPanel categories = new JPanel();
        categories.setBackground(new Color(160,0,0));
//        main.add(categories, BorderLayout.LINE_START);

        GridLayout menuGrid = new GridLayout(13,1);
            categories.setLayout(menuGrid);

        JLabel cate = new JLabel("Categories");
            cate.setFont(txt);
            cate.setForeground(new Color(255, 228, 0)); 
            categories.add(cate);

        JButton action = new JButton("Action and Adventure");
            action.addActionListener(controller);
            action.setActionCommand("action");
        JButton comedy = new JButton("Comedy");
            comedy.addActionListener(controller);
            comedy.setActionCommand("comedy");
        JButton drama = new JButton("Drama");
            drama.addActionListener(controller);
            drama.setActionCommand("drama");
        JButton fantasy = new JButton("Fantasy");
            fantasy.addActionListener(controller);
            fantasy.setActionCommand("fantasy");
        JButton horror = new JButton("Horror");
            horror.addActionListener(controller);
            horror.setActionCommand("horror");
        JButton mystery = new JButton("Mystery");
            mystery.addActionListener(controller);
            mystery.setActionCommand("mystery");
        JButton romance = new JButton("Romance");
            romance.addActionListener(controller);
            romance.setActionCommand("romance");
        JButton scifi = new JButton("Sci-Fi");
            scifi.addActionListener(controller);
            scifi.setActionCommand("scifi");
        JButton thriller = new JButton("Trhiller");
            thriller.addActionListener(controller);
            thriller.setActionCommand("thriller");
        JButton western = new JButton("Western");
            western.addActionListener(controller);
            western.setActionCommand("western");

            categories.add(action);
            categories.add(comedy);
            categories.add(drama);
            categories.add(fantasy);
            categories.add(horror);
            categories.add(mystery);
            categories.add(romance);
            categories.add(scifi);
            categories.add(thriller);
            categories.add(western);

        JLabel hiw = new JLabel("How it Works?");
            hiw.setFont(txt);
            hiw.setForeground(new Color(255, 228, 0)); 
            categories.add(hiw);

        JButton faq = new JButton("FAQ");
            faq.addActionListener(controller);
            faq.setActionCommand("faq");
            categories.add(faq);
                
        return categories;
    }
    
    
    public JPanel moviePanel(){
        
        BorderLayout movieL = new BorderLayout();
        JPanel moviePanel = new JPanel();
            moviePanel.setLayout(movieL);
            moviePanel.add(moviePanelHeader(),BorderLayout.NORTH);

        sp = new ScrollPane();
        GridLayout movieListL = new GridLayout(0,5); //makes the grid to have 5 rows and dynamic number of columns
        JPanel movieListP = new JPanel();  //panel for the movie list (images and title tiles
            movieListP.setLayout(movieListL);
            moviePanel.add(sp, BorderLayout.CENTER);
            sp.add(movieListP);
            

        countResult = controller.allMovieCount;
        countIndex = 0; //initialized the count index
        
        JPanel[] movieArray = new JPanel[countResult];
        
        if(countResult == 0){
            System.out.println("No movies found"); //will change later
        }
        else{
            for(int i=0;i<countResult;i++){
                
                movieArray[i] = new JPanel();
                JPanel movieTile = new JPanel();
//                movieTile.setBorder(new EmptyBorder(10,10,10,10));
                    JPanel movieDetails = new JPanel();
                    movieDetails.setLayout(new BoxLayout(movieDetails, BoxLayout.Y_AXIS));
                    movieTile.add(movieDetails);
                        ImageIcon movieImage = new ImageIcon(getClass().getResource("images/"+controller.movies[i].getMovie_code()+".jpg"));
                        movieImage.setImage(movieImage.getImage().getScaledInstance(110, 200, 100));
                        JLabel itt = new JLabel(movieImage);
                        movieDetails.add(itt);
                        JLabel movieTitle = new JLabel(controller.movies[i].getTitle());
                        movieDetails.add(movieTitle);
                        JLabel movieDate = new JLabel(String.valueOf(controller.movies[i].getRelease_year()));
                        movieDetails.add(movieDate);
                        JLabel movieGenre = new JLabel(controller.movies[i].getGenre());
                        movieDetails.add(movieGenre);
                        JButton rentB = new JButton("RENT");
                        movieDetails.add(rentB);
                
                
                        
                        
              movieArray[i].add(movieTile);
              movieListP.add(movieArray[i]);
            }
            
            JPanel backButtonP = new JPanel(new FlowLayout(FlowLayout.TRAILING));
            moviePanel.add(backButtonP, BorderLayout.SOUTH);
                JButton backButton = new JButton("Home");
                backButtonP.add(backButton);
                
            
            
        }
//        moviePanel.revalidate();
//        moviePanel.repaint();
        return moviePanel;
    }
 
    
    public JPanel movieSearchPanel(){

        
        BorderLayout movieL = new BorderLayout();
        JPanel movieSearchPanel = new JPanel();
            movieSearchPanel.setLayout(movieL);
            movieSearchPanel.add(moviePanelHeader(),BorderLayout.NORTH);
        sp = new ScrollPane();
        GridLayout movieListL = new GridLayout(0,5); //makes the grid to have 5 rows and dynamic number of columns
        JPanel movieListP = new JPanel();  //panel for the movie list (images and title tiles
            movieListP.setLayout(movieListL);
            movieSearchPanel.add(sp, BorderLayout.CENTER);
            sp.add(movieListP);
            
        countResult = controller.countSearch;
        countIndex = 0; //initialized the count index
        System.out.println("V"+countResult);
        JPanel[] movieArray = new JPanel[countResult];
        
//        if(countResult == 0){
//            System.out.println("No movies found"); //will change later
//        }
//        else{
            for(int i=0;i<countResult;i++){
                
                movieArray[i] = new JPanel();
                JPanel movieTile = new JPanel();
//                movieTile.setBorder(new EmptyBorder(10,10,10,10));
                    JPanel movieDetails = new JPanel();
                    movieDetails.setLayout(new BoxLayout(movieDetails, BoxLayout.Y_AXIS));
                    movieTile.add(movieDetails);
                        ImageIcon movieImage = new ImageIcon(getClass().getResource("images/"+controller.searchMovieResult[i][5]+".jpg"));
                        movieImage.setImage(movieImage.getImage().getScaledInstance(110, 200, 100));
                        JLabel itt = new JLabel(movieImage);
                        movieDetails.add(itt);
                        JLabel movieTitle = new JLabel(controller.searchMovieResult[i][0]);
                        movieDetails.add(movieTitle);
                        JLabel movieDate = new JLabel(controller.searchMovieResult[i][2]);
                        movieDetails.add(movieDate);
                        JLabel movieGenre = new JLabel(controller.searchMovieResult[i][1]);
                        movieDetails.add(movieGenre);
                        JButton rentB = new JButton("RENT");
                        movieDetails.add(rentB);
                
                
                        
                        
              movieArray[i].add(movieTile);
              movieListP.add(movieArray[i]);
            }
//        }
        return movieSearchPanel;
    }
    
    
    public JPanel movieCategories() {
    BorderLayout movieL = new BorderLayout();
        JPanel movieCategories = new JPanel();
            movieCategories.setLayout(movieL);
//            movieSection.add(moviePanel);
        
        JPanel moviePanelHeader = new JPanel();  //contains the "New Arrivals, etc" as well as the sort buttons
        movieCategories.add(moviePanelHeader,BorderLayout.NORTH);
            String[] sortOption = {"Name","Popularity","Genre","Release Date"};
            JComboBox sort = new JComboBox(sortOption);
            JButton sortConfirm = new JButton("Sort");
            moviePanelHeader.add(sort);
            moviePanelHeader.add(sortConfirm);
        
        sp = new ScrollPane();
        GridLayout movieListL = new GridLayout(0,5); //makes the grid to have 5 rows and dynamic number of columns
        JPanel movieListP = new JPanel();  //panel for the movie list (images and title tiles
            movieListP.setLayout(movieListL);
            movieCategories.add(sp, BorderLayout.CENTER);
            sp.add(movieListP);
            
//        JPanel movieLoop = new JPanel();
//        movieListP.add(movieLoop);
        
        countResult = controller.categoriesCount;
        countIndex = 0; //initialized the count index
        
        JPanel[] movieArray = new JPanel[countResult];
        
        if(countResult == 0){
            System.out.println("No movies found"); //will change later
        }
        else{
            for(int i=0;i<countResult;i++){
                
                movieArray[i] = new JPanel();
                JPanel movieTile = new JPanel();
//                movieTile.setBorder(new EmptyBorder(10,10,10,10));
                    JPanel movieDetails = new JPanel();
                    movieDetails.setLayout(new BoxLayout(movieDetails, BoxLayout.Y_AXIS));
                    movieTile.add(movieDetails);
                        ImageIcon movieImage = new ImageIcon(getClass().getResource("images/"+controller.categoriesResult[i][5]+".jpg"));
                        movieImage.setImage(movieImage.getImage().getScaledInstance(110, 200, 100));
                        JLabel itt = new JLabel(movieImage);
                        movieDetails.add(itt);
                        JLabel movieTitle = new JLabel(controller.categoriesResult[i][0] + " ("+controller.categoriesResult[i][2]+")");
                        movieDetails.add(movieTitle);
                        JLabel movieGenre = new JLabel(controller.categoriesResult[i][1]);
                        movieDetails.add(movieGenre);
                        JButton rentB = new JButton("RENT");
                        movieDetails.add(rentB);
                
                
                        
                        
              movieArray[i].add(movieTile);
              movieListP.add(movieArray[i]);
            }
        }
        return movieCategories;
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    // Sorting View Panels ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public JPanel moviePanelSort(){ //View panel for all the movies
        
        BorderLayout movieL = new BorderLayout();
        JPanel moviePanelSort = new JPanel();
            moviePanelSort.setLayout(movieL);
//            moviePanelSort.add(moviePanelHeader(),BorderLayout.NORTH);
        
        
        sp = new ScrollPane();
        GridLayout movieListL = new GridLayout(0,5); //makes the grid to have 5 rows and dynamic number of columns
        JPanel movieListP = new JPanel();  //panel for the movie list (images and title tiles
            movieListP.setLayout(movieListL);
            moviePanelSort.add(sp, BorderLayout.CENTER);
            sp.add(movieListP);
            

        countResult = controller.allMovieCount;
        countIndex = 0; //initialized the count index
        
        JPanel[] movieArray = new JPanel[countResult];
        
        if(countResult == 0){
            System.out.println("No movies found"); //will change later
        }
        else{
            for(int i=0;i<countResult;i++){
                
                movieArray[i] = new JPanel();
                JPanel movieTile = new JPanel();
//                movieTile.setBorder(new EmptyBorder(10,10,10,10));
                    JPanel movieDetails = new JPanel();
                    movieDetails.setLayout(new BoxLayout(movieDetails, BoxLayout.Y_AXIS));
                    movieTile.add(movieDetails);
                        ImageIcon movieImage = new ImageIcon(getClass().getResource("images/"+controller.moviesActive[i][5]+".jpg"));
                        movieImage.setImage(movieImage.getImage().getScaledInstance(110, 200, 100));
                        JLabel itt = new JLabel(movieImage);
                        movieDetails.add(itt);
                        JLabel movieTitle = new JLabel(controller.moviesActive[i][0]);
                        movieDetails.add(movieTitle);
                        JLabel movieDate = new JLabel(controller.moviesActive[i][2]);
                        movieDetails.add(movieDate);
                        JLabel movieGenre = new JLabel(controller.moviesActive[i][1]);
                        movieDetails.add(movieGenre);
                        JButton rentB = new JButton("RENT");
                        movieDetails.add(rentB);
                
                
                        
                        
              movieArray[i].add(movieTile);
              movieListP.add(movieArray[i]);
            }
        }
        
        moviePanelSort.add(moviePanelHeader(),BorderLayout.NORTH);
        
        
//        moviePanel.revalidate();
//        moviePanel.repaint();
        return moviePanelSort;
    }
    
    
    public JPanel movieSearchPanelSort(){ //View panel for all the movies

        
        BorderLayout movieL = new BorderLayout();
        JPanel movieSearchPanelSort = new JPanel();
            movieSearchPanelSort.setLayout(movieL);
//            movieSearchPanelSort.add(moviePanelHeader(),BorderLayout.NORTH);
        
        sp = new ScrollPane();
        GridLayout movieListL = new GridLayout(0,5); //makes the grid to have 5 rows and dynamic number of columns
        JPanel movieListP = new JPanel();  //panel for the movie list (images and title tiles
            movieListP.setLayout(movieListL);
            movieSearchPanelSort.add(sp, BorderLayout.CENTER);
            sp.add(movieListP);
            
        countResult = controller.countSearch;
        countIndex = 0; //initialized the count index
        System.out.println("V"+countResult);
        JPanel[] movieArray = new JPanel[countResult];
        
//        if(countResult == 0){
//            System.out.println("No movies found"); //will change later
//        }
//        else{
            for(int i=0;i<countResult;i++){
                
                movieArray[i] = new JPanel();
                JPanel movieTile = new JPanel();
//                movieTile.setBorder(new EmptyBorder(10,10,10,10));
                    JPanel movieDetails = new JPanel();
                    movieDetails.setLayout(new BoxLayout(movieDetails, BoxLayout.Y_AXIS));
                    movieTile.add(movieDetails);
                        ImageIcon movieImage = new ImageIcon(getClass().getResource("images/"+controller.moviesActive[i][5]+".jpg"));
                        movieImage.setImage(movieImage.getImage().getScaledInstance(110, 200, 100));
                        JLabel itt = new JLabel(movieImage);
                        movieDetails.add(itt);
                        JLabel movieTitle = new JLabel(controller.moviesActive[i][0]);
                        movieDetails.add(movieTitle);
                        JLabel movieDate = new JLabel(controller.moviesActive[i][2]);
                        movieDetails.add(movieDate);
                        JLabel movieGenre = new JLabel(controller.moviesActive[i][1]);
                        movieDetails.add(movieGenre);
                        JButton rentB = new JButton("RENT");
                        movieDetails.add(rentB);
                
                
                        
                        
              movieArray[i].add(movieTile);
              movieListP.add(movieArray[i]);
            }
//        }

        movieSearchPanelSort.add(moviePanelHeader(),BorderLayout.NORTH);

        return movieSearchPanelSort;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public JPanel moviePanelHeader(){
//        GridLayout moviePanelHeaderL = new GridLayout(1,2);
        JPanel moviePanelHeader = new JPanel();  //contains the "New Arrivals, etc" as well as the sort buttons
//        BoxLayout moviePanelHeaderL = new BoxLayout(moviePanelHeader,BoxLayout.X_AXIS);
        BoxLayout moviePanelHeaderL = new BoxLayout(moviePanelHeader,BoxLayout.X_AXIS);
        moviePanelHeader.setLayout(moviePanelHeaderL);
//        moviePanel.add(moviePanelHeader,BorderLayout.NORTH);
            JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
            labelPanel.setAlignmentX(LEFT_ALIGNMENT);//align not working
            moviePanelHeader.add(labelPanel);
                JLabel label = new JLabel(headerLabel);
                labelPanel.add(label);

            JPanel sortCB = new JPanel(new FlowLayout(FlowLayout.TRAILING));
            sortCB.setAlignmentX(RIGHT_ALIGNMENT);//align not working
            moviePanelHeader.add(sortCB);
                String[] sortOption = {"Name","Popularity","Genre","Release Date"};
                sort = new JComboBox(sortOption);
                sort.setVisible(true); // Prevents the dropdown to be hidden behind another component; Always display it on top
                sort.setLightWeightPopupEnabled(true);
//                sort.setPopupVisible(true);
                JButton sortConfirm = new JButton("Sort");
                sortCB.add(sort);
                sortConfirm.addActionListener(controller);
                sortConfirm.setActionCommand("sort");
                sortCB.add(sortConfirm);
            
        return moviePanelHeader;    
    }
    
    
    //faq frame
    public void faq(){
        String[] questions={"Questions","Answers"};
        String[][] answers={{"How long can I keep the movie?","You can keep the movie for 3 days"},
                {"How much cost each movie?","Each movie costs £3,99"},
                {"Why do I need to pay a security deposit?","We change a security deposit in case any damage cause on the disk, scretches for example"},
                {"How much is the security deposit?","The security desposit is £7,99"},
                {"What happen if I do not return the disk?","We will automatically charge on your credit card the security deposit"}};
        
        faq = new JFrame();
            faq.setVisible(true);
            faq.setSize(600, 600);
            faq.setTitle("Xtra Vision");
            BorderLayout welcomeLayout = new BorderLayout();
            faq.setLayout(welcomeLayout);
            
                    JPanel search = new JPanel();
            faq.add(search, BorderLayout.PAGE_START);
            search.setBackground(new Color(160,0,0));
            GridLayout searchGrid = new GridLayout(1,4);
                search.setLayout(searchGrid);
        //image    
        ImageIcon logoxtra = new ImageIcon(getClass().getResource("image/logoxtravision.png"));        
            logoxtra.setImage(logoxtra.getImage().getScaledInstance(150, 45, 100));    
            JButton logo = new JButton();
            logo.setIcon(logoxtra); //button with image needs design fix
                logo.addActionListener(controller);
                logo.setActionCommand("logo");
                search.add(logo);
                
            JTextField searchBar = new JTextField();
                search.add(searchBar);   
            JButton searchButton = new JButton("Search");
                search.add(searchButton);
            JButton cart = new JButton("My Cart");
                search.add(cart);
                
        //panel for the questions and answers
        JPanel p1 = new JPanel();
            faq.add(p1, BorderLayout.CENTER);
           
        JTable qaa = new JTable(answers,questions);
        JScrollPane qaaScroll = new JScrollPane(qaa);
            faq.add(qaa);
                
          
    }
}
