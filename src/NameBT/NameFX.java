package NameBT;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class NameFX extends Application {
	//set public variables
	Scanner scanner = new Scanner(System.in);
	Person fRoot;
	Person sRoot;
	//set panes
	private final Pane pane1 = new Pane();
	private final Pane pane2 = new Pane();

	//set button   
	private final Button draw1 = new Button("Draw tree by fisrtname");
	private final Button draw2 = new Button("Draw tree by surname");
	private final Button find = new Button("find long name");
	private final Button compare1 = new Button("fisrtname longer than surname ");
	private final Button compare2 = new Button("surname longer than firstname ");
	
	//set textfield
	private final TextField textField = new TextField();
	private final TextArea textArea = new TextArea();


	@Override
	public void start(Stage primaryStage) throws Exception {
		//only load once
		loadPerson();
		//set stage
		primaryStage.setTitle("Name BST");
		primaryStage.setScene(layout());
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("Settings-32.png")));
		primaryStage.show();

	}
	
	public Scene layout() throws NumberFormatException {
		//set hbox as outside pane
	    HBox hbox = new HBox(10);
	    hbox.setPadding(new Insets(5,5,5,5));
	    hbox.setStyle("-fx-background-color:lightgrey");
		
		//add functions to buttons
	       draw1.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						pane1.getChildren().removeAll();
						drawTree(fRoot,300,50,150,50);
					}
				});
	       
	       draw2.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						pane1.getChildren().removeAll();
						drawTree(sRoot,300,50,150,50);
					}
				});
	       
	       find.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						textArea.clear();
						displaylongname(dfFindLongNames(fRoot, Integer.parseInt(textField.getText())));
					}
				});
	       
	       compare1.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						textArea.clear();
						displayname(dfFindNames(fRoot));
					}
				});
	   
	       compare2.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						textArea.clear();
						displayname1(dfFindNames1(fRoot));
					}
				});
	    
	    //set the reversal buttons for first name tree
	    MenuItem preorderF = new MenuItem("pre-order");
        MenuItem postorderF = new MenuItem("post-order");
        MenuItem inorderF = new MenuItem("in-order");
        MenuItem breathdthfirstF = new MenuItem("breadthfirst");
        
        preorderF.setOnAction(event -> {
    		textArea.clear();
        	printNamesPreOrderFN(fRoot);});
        postorderF.setOnAction(event -> {
    		textArea.clear();
        	printNamesPostOrderFN(fRoot);});
        inorderF.setOnAction(event -> {
    		textArea.clear();
    		printNamesInOrderFN(fRoot);});
        breathdthfirstF.setOnAction(event -> {
    		textArea.clear();
    		breadthFirstTraversalF(fRoot);});

        MenuButton menuButtonF = new MenuButton("first name reversal", null, preorderF, postorderF , inorderF,breathdthfirstF);
		ImageView settingimageF = new ImageView(getClass().getResource("right.png").toExternalForm());
		settingimageF.setFitHeight(10);
		settingimageF.setFitWidth(10);
        menuButtonF.setGraphic(settingimageF);

		
		//set the reversal buttons for surname tree
		MenuItem preorderS = new MenuItem("pre-order");
        MenuItem postorderS = new MenuItem("post-order");
        MenuItem inorderS = new MenuItem("in-order");
        MenuItem breathdthfirstS = new MenuItem("breadthfirst");
      
        preorderS.setOnAction(event -> {
    		textArea.clear();
        	printNamesPreOrderSN(sRoot);});
        postorderS.setOnAction(event -> {
    		textArea.clear();
        	printNamesPostOrderSN(sRoot);});
        inorderS.setOnAction(event -> {
    		textArea.clear();
    		printNamesInOrderSN(sRoot);});
        breathdthfirstS.setOnAction(event -> {
    		textArea.clear();
    		breadthFirstTraversalS(sRoot);});
        
        MenuButton menuButtonS = new MenuButton("sur name reversal", null, preorderS, postorderS , inorderS,breathdthfirstS);
		ImageView settingimageS = new ImageView(getClass().getResource("right.png").toExternalForm());
		settingimageS.setFitHeight(10);
		settingimageS.setFitWidth(10);
        menuButtonS.setGraphic(settingimageS);
        
        //put two reversal buttons together
		VBox menusetting = new VBox(menuButtonF,menuButtonS);
		menusetting.setPrefSize(100,100);
		menusetting.setPadding(new Insets(5, 5, 5, 5));
		menusetting.setAlignment(Pos.CENTER);
	    
		//add textfield for find button
		VBox load = new VBox(10);
		textField.setFont(Font.font(10));
		textField.setAlignment(Pos.CENTER);// Align text to center
		textField.setPrefWidth(10);// Set width
		textField.setPrefHeight(10);// Set height
		textField.setPromptText("Enter number of character length");
		load.getChildren().addAll(textField,find);
		load.setAlignment(Pos.CENTER);
		VBox.setVgrow(textField, Priority.ALWAYS);
	
	    //set vbox as side to put buttons
	    VBox vbox = new VBox(10);
	    vbox.setPrefWidth(100);
	    vbox.setPadding(new Insets(5,5,5,5));
	    vbox.setPrefSize(200,200);
	    vbox.setAlignment(Pos.CENTER);
	    vbox.getChildren().addAll(draw1,draw2,load,compare1,compare2,menusetting);
	    
	    //set inside pane size
	    pane1.setPrefWidth(getVisualScreenWidth()/2);
	    pane1.setPrefHeight(getVisualScreenHeight()/1.2);
	    pane1.setStyle("-fx-background-color:lightblue");
	    
	    //set button size
	    draw1.setPrefWidth(200);
	    draw2.setPrefWidth(200);
	    find.setPrefWidth(200);
	    compare1.setPrefWidth(200);
	    compare2.setPrefWidth(200);
	
	    //set 
	    textArea.setFont(Font.font(12));
	    textArea.setPrefWidth(getVisualScreenWidth()/1.2 );// Set width
	    textArea.setPrefHeight(100);// Set height
	    
	    //put left/middle/right parts into outside pane
		hbox.getChildren().addAll(vbox,pane1,textArea);
		pane2.getChildren().add(hbox);

		// set scene
		Scene scene = new Scene(pane2, getVisualScreenWidth()/1.2 , getVisualScreenHeight()/1.2 );
			return scene;
		 }
	
	//show results as a text at textarea
	public void textArea(Person p) {
		textArea.appendText(p.getFirstname()+p.getSurname());
		textArea.appendText("\n");
	}
	
	public static double getVisualScreenWidth() {
		return Screen.getPrimary().getVisualBounds().getWidth();
	}

	public static double getVisualScreenHeight() {
		return Screen.getPrimary().getVisualBounds().getHeight();
	}
	
	//below is BST methods
	//load person from file and add them into firstname tree and surname tree
	public void loadPerson() {
		try {
			Scanner scan = new Scanner(new File("mswdev.csv"));
			while (scan.hasNext()) {
				String Fname = scan.next().trim();
				// if we use the scan. twice we should consider where if the pointer
				String Sname = scan.nextLine().trim();
				if (fRoot == null) {
					fRoot = new Person(Fname, Sname);
				} else {
					buildTree(fRoot, new Person(Fname, Sname));
				}
				if (sRoot == null) {
					sRoot = new Person(Sname, Fname);
				} else {
					buildTree(sRoot, new Person(Sname, Fname));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//pass the person as nodes to build the binary tree
	public void buildTree(Person currentP, Person newP) {
		if (currentP.getFirstname().compareTo(newP.getFirstname()) > 0) {
			if (currentP.getBefore() == null) {
				currentP.setBefore(newP);
			} else {
				buildTree(currentP.getBefore(), newP);
			}
		} else {
			if (currentP.getAfter() == null) {
				currentP.setAfter(newP);
			} else {
				buildTree(currentP.getAfter(), newP);
			}
		}
	}
	
	//Visualize the binary tree in inside pane
	public void drawTree(Person p,int x,int y,int xd,int yd) {
		if(p.getBefore()!= null) {
			//draw left line
			Line line = new Line(x,y,x-xd,y+yd);
			pane1.getChildren().add(line);
			drawTree(p.getBefore(),x-xd,y+yd,xd/2,yd);
		}
		if(p.getAfter()!= null) {
			//draw right line
			Line line = new Line(x,y,x+xd,y+yd);
			pane1.getChildren().add(line);
			drawTree(p.getAfter(),x+xd,y+yd,xd/2,yd);
		}
		p.setLayoutX(x-8);
		p.setLayoutY(y-8);
		p.setText(p.toString().toUpperCase());
		p.setStyle("-fx-background-color:yellow");
		p.setTooltip(new Tooltip(p.getFirstname()+p.getSurname()));
		pane1.getChildren().add(p);
		}

	//print firstname by in-order 
	public void printNamesInOrderFN(Person fRoot) {
		if (fRoot != null) {
			printNamesInOrderFN(fRoot.getBefore());
			textArea(fRoot);
			printNamesInOrderFN(fRoot.getAfter());
		}
	}
	
	//print surname by in-order 
	public void printNamesInOrderSN(Person sRoot) {
		if (sRoot != null) {
			printNamesInOrderSN(sRoot.getBefore());
			textArea(sRoot);
			printNamesInOrderSN(sRoot.getAfter());
		}
	}
	
	//print firstname by pre-order
	public void printNamesPreOrderFN(Person fRoot) {
		if (fRoot != null) {
			textArea(fRoot);
			printNamesPreOrderFN(fRoot.getBefore());
			printNamesPreOrderFN(fRoot.getAfter());
		}
	}	
	
	//print surname by pre-order
	public void printNamesPreOrderSN(Person sRoot) {
		if (sRoot != null) {
			textArea(sRoot);
			printNamesPreOrderSN(sRoot.getBefore());
			printNamesPreOrderSN(sRoot.getAfter());
		}
	}
	
	//print firstname by post-order
	public void printNamesPostOrderFN(Person fRoot) {
		if (fRoot != null) {
			printNamesPostOrderFN(fRoot.getBefore());
			printNamesPostOrderFN(fRoot.getAfter());
			textArea(fRoot);
		}
	}
	
	//print surname by post-order
	public void printNamesPostOrderSN(Person sRoot) {
		if (sRoot != null) {
			printNamesPostOrderSN(sRoot.getBefore());
			printNamesPostOrderSN(sRoot.getAfter());
			textArea(sRoot);
		}
	}

	// Breadth first traversal for firstname
	public void breadthFirstTraversalF(Person froot) {
		ArrayDeque<Person> nodesQueue = new ArrayDeque<>();
		nodesQueue.offer(froot);

		while (!nodesQueue.isEmpty()) {
			Person p = nodesQueue.poll();
			textArea(p);
			if (p.getBefore() != null) {
				nodesQueue.offer(p.getBefore());
			}

			if (p.getAfter() != null) {
				nodesQueue.offer(p.getAfter());
			}
		}

	}
	
	// Breadth first traversal for surname
	public void breadthFirstTraversalS(Person sroot) {
		ArrayDeque<Person> nodesQueue = new ArrayDeque<>();
		nodesQueue.offer(sroot);

		while (!nodesQueue.isEmpty()) {
			Person p = nodesQueue.poll();
			textArea(p);
			if (p.getBefore() != null) {
				nodesQueue.offer(p.getBefore());
			}

			if (p.getAfter() != null) {
				nodesQueue.offer(p.getAfter());
			}
		}
	}
	
	//find firstname or surname is longer than X
	public void displaylongname(List<Person> listOfLong) {
		for (Person l:listOfLong) {
			textArea(l);
		}
	}
	
	public List<Person> dfFindLongNames(Person p, int Length){
        List<Person> listOfLong = new ArrayList<Person>();
        dfFindLongNamesHelper(p, Length, listOfLong);
        return listOfLong;
    }
	
	public void  dfFindLongNamesHelper(Person p, int Length, List<Person> listOfLong){
        if (p!=null){
            if (p.getFirstname().length()> Length||p.getSurname().length()> Length) { 
            	listOfLong.add(p); 
            	}
            dfFindLongNamesHelper(p.getBefore(), Length, listOfLong); 
            dfFindLongNamesHelper(p.getAfter(),Length, listOfLong); 
        }
    }
	
	//find firstname longer than surname
	public void displayname(List<Person> listOfLong) {
		for (Person l:listOfLong) {
			textArea(l);
		}
	}
	
	public List<Person> dfFindNames(Person p){
        List<Person> listOfname = new ArrayList<Person>();
        dfFindNamesHelper(p, listOfname);
        return listOfname;
    }
	
	public void  dfFindNamesHelper(Person p, List<Person> listOfname){
        if (p!=null){
            if (p.getFirstname().length()>p.getSurname().length()) { 
            	listOfname.add(p); 
            	}
            dfFindNamesHelper(p.getBefore(),listOfname); 
            dfFindNamesHelper(p.getAfter(),listOfname); 
        }
    }
	
	//find firstname shorter than surname
	public void displayname1(List<Person> listOfLong) {
		for (Person l:listOfLong) {
			textArea(l);
		}
	}
	
	public List<Person> dfFindNames1(Person p){
        List<Person> listOfname = new ArrayList<Person>();
        dfFindNamesHelper1(p, listOfname);
        return listOfname;
    }
	
	public void  dfFindNamesHelper1(Person p, List<Person> listOfname){
        if (p!=null){
            if (p.getFirstname().length()<p.getSurname().length()) { 
            	listOfname.add(p); 
            	}
            dfFindNamesHelper1(p.getBefore(),listOfname); 
            dfFindNamesHelper1(p.getAfter(),listOfname); 
        }
    }

	public static void main(String[] args) {
		Application.launch(args);

	}
}
