//**AMEENA**//
//**9/27/17**//
//Histogram**//

//**package name**//
package histogram;

//**All the required import**//
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class Histogram extends Application 
{
    private static Comparator <? super Integer> cmprtr; 

    ArrayList <Integer> list = new ArrayList();
    ArrayList <XYChart.Data> dataList = new ArrayList<>();

    @Override
    public void start(Stage mainStage) 
    {
        Random rand = new Random();
        int ranNumber = rand.nextInt(50);
        
        for (int counter = 0; counter < ranNumber; counter++) 
        {
           list.add(rand.nextInt(50));
        }
        
        //**GENEREATED RANDOM NUMBER**//
        System.out.println("GENEREATED RANDOM NUMBER");
        for (int counter = 0; counter < ranNumber; counter++) 
        {
            System.out.print(list.get(counter)+ " ");
        }
        
        //**DISPLAYS THE COUNT OF WORD BEFORE SORTED**//
        System.out.println();
        System.out.println("COUNT OF WORDS BEFORE SORTED ");
        System.out.print(list.size() + " ");

        //**USING THE COMPARATOR TO COMPARE THE TWO**//
        list.sort(cmprtr);

        //**SORTED FROM MAXIMUM TO MINIMUM AND REMOVED COPIES**//
        System.out.println();
        System.out.println("SORTED FROM MAXIMUM TO MINIMUM ");
        int size = list.size();
        
        
        /**For loop to display the sorted numbers**/
        for (int counter = 0; counter < size; counter++) 
        {
            System.out.print(list.get(counter) + " ");
        }
        
        //**To display the date of the number of counts**//
        System.out.println();
        System.out.println("COUNT OF WORDS AFTER SORTED ");
        System.out.print(list.size() + " ");

        GridPane pane = new GridPane();

        //**LABELS SCENE1**//
        Label max = new Label("MAXIMUM");
        TextField maxField = new TextField();
        maxField.setEditable(false);
        maxField.setText(Integer.toString(list.get(list.size() - 1)));
        maxField.setMaxWidth(500);
                
        GridPane.setConstraints(max, 0, 0);
        GridPane.setConstraints(maxField, 0, 1);
        
        
        Label min = new Label("MINIMUM");
        TextField minField = new TextField();
        minField.setText(Integer.toString(list.get(0)));
        minField.setEditable(false);
        
        GridPane.setConstraints(min, 0, 2);
        GridPane.setConstraints(minField, 0, 7);
        
        Label range = new Label("RANGE");
        TextField rangeField = new TextField();
        rangeField.setText(Integer.toString(list.get(list.size() - 1) - list.get(0)));
        rangeField.setEditable(false);
        
        GridPane.setConstraints(range, 0, 8);
        GridPane.setConstraints(rangeField, 0, 9);
        
        Label numberOfInputs = new Label("NUMBER OF INPUTS");
        TextField numInputsField = new TextField();
        numInputsField.setText(Integer.toString(list.size()));
        numInputsField.setEditable(false);

        GridPane.setConstraints(numberOfInputs, 0, 14);
        GridPane.setConstraints(numInputsField, 0, 15);
        
        Label bins = new Label("ENTER THE NUMBER OF BINS");
        Label withInput = new Label("ENTER A NUMBER GREATHER THEN 1");
        withInput.setVisible(false);
         TextField binsInput = new TextField();
        GridPane.setConstraints(bins, 0, 16);
        GridPane.setConstraints(binsInput, 0, 21);
       
        Button enter = new Button("ENTER"); 
        GridPane.setConstraints(enter, 0, 22);
        GridPane.setConstraints(withInput, 0, 23);
        
        pane.getChildren().addAll(max, min, range, numberOfInputs, maxField, minField, rangeField, numInputsField, bins, binsInput, withInput, enter);

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
                
        final BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);

        XYChart.Series series1 = new XYChart.Series();

        //**TRY CATCH BLOCK**//
        enter.setOnAction(e -> 
        {
            try 
            {
                if (Integer.parseInt(binsInput.getText()) > 1)
                {
                    int a = 0;
                    int b = 0;
                    int c = 0;
                    int frequency = ((list.get(list.size() - 1)) / (Integer.parseInt(binsInput.getText()))) + 1;
                    
                    for (int counter = 0; counter < Integer.parseInt(binsInput.getText()); counter++)
                    {
                        for (int d = 0; d < list.size(); d++) 
                        {
                            if ((list.get(d) >= (c + b)) && (list.get(d) <= (c + frequency + b))) 
                            {
                                a++;
                            }
                        }

                        dataList.add(counter, new XYChart.Data((c + b) + " - " + (c + frequency), a));
                        series1.getData().add(dataList.get(counter));
                        
                        c += frequency;
                        b = 1;
                        a = 0;
                    }
                    
                    barChart.getData().add(series1);
                    
                   Scene secondaryStage = new Scene(barChart, 1000, 1000);
                   
                    mainStage.setScene(secondaryStage);
                } 
               else 
                {
                    withInput.setVisible(true);
                }

            }
            catch (NumberFormatException ex) 
            {
                withInput.setVisible(true);
            }
        });

        Scene primaryScene = new Scene(pane, 1000, 1000);

        mainStage.setTitle("HISTOGRAM");
        
        mainStage.setScene(primaryScene);
        
        mainStage.show();

        series1.chartProperty().addListener(e ->
        {
            ArrayList<Integer> xValueList = new ArrayList<>();
            
            for (int counter = 0; counter < dataList.size(); counter++)
            {
                if ((Integer) dataList.get(counter).getYValue() > 0) 
                {
                    xValueList.add((Integer) dataList.get(counter).getYValue());
                }

            }

            xValueList.sort(cmprtr);

            for (int counter = 0; counter < dataList.size(); counter++) 
            {
                if (((Integer) dataList.get(counter).getYValue()) == (xValueList.get(xValueList.size() - 1))) 
                {
                    dataList.get(counter).nodeProperty().addListener(new ChangeListener<Node>() 
                    {
                        
                        @Override
                        public void changed(ObservableValue<? extends Node> ovalue, Node nNode, Node eNode) 
                        {
                            if (eNode != null) 
                            {
                                eNode.setStyle("-fx-bar-fill: red;");
                            }
                        }
                    });
                }

                if (((Integer) dataList.get(counter).getYValue()) == (xValueList.get(0))) 
                {
                    dataList.get(counter).nodeProperty().addListener(new ChangeListener<Node>() 
                    {
                        
                       @Override
                        public void changed(ObservableValue<? extends Node> ovalue, Node nNode, Node eNode)
                        {
                            if (eNode != null) 
                            {
                                eNode.setStyle("-fx-bar-fill: blue;");
                            }
                        }
                    });
                }
            }
        });
    }
       
    //**main method to launch the program**//
    public static void main(String[] args) 
    {
        launch(args);
    }
}