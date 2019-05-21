package Lab.Windows;

import Lab.ServerClient.Client;
import Lab.ServerClient.Window.ClientWindow;
import Lab.Things.Car;
import Lab.Things.Details;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CarWindow extends JPanel {

    private int width;
    private int height;
    private boolean visible;
    private JTextArea nameTextArea;
    private JTextArea propertyTextArea;
    private ArrayList<JCheckBox> detailsNeedSkill;
    private ArrayList<JTextArea> detailsDegree;
    private ArrayList<JSlider> detailsQuality;

    public CarWindow(){
        visible = true;
        width = 720;
        height = 480;

        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));

        width = 680;

        drawWindow();
    }

    public void drawWindow(){
        removeAll();
        detailsNeedSkill = new ArrayList<>();
        detailsDegree = new ArrayList<>();
        detailsQuality = new ArrayList<>();

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(2, 2, 2, 2);
        constraints.gridx = 0;
        constraints.gridy = 0;

        ResourceBundle bundle;
        bundle = ResourceBundle.getBundle("resource.lab4",
                ClientWindow.getLoc());
        JLabel nameLabel = new JLabel(bundle.getString("tableName"));
        nameLabel.setPreferredSize(new Dimension(width/4, 20));
        nameLabel.setMinimumSize(new Dimension(width/4, 20));
        nameLabel.setMaximumSize(new Dimension(width/4, 20));
        nameTextArea = new JTextArea();
        nameTextArea.setPreferredSize(new Dimension(width/4, 20));
        nameTextArea.setMinimumSize(new Dimension(width/4, 20));
        nameTextArea.setMaximumSize(new Dimension(width/4, 20));

        JPanel panelOne = new JPanel(new GridBagLayout());
        GridBagConstraints constraintsOne = new GridBagConstraints();
        constraintsOne.anchor = GridBagConstraints.NORTH;
        constraintsOne.insets = new Insets(2, 2, 2, 2);
        constraintsOne.gridx = 0;
        constraintsOne.gridy = 0;

        panelOne.add(nameLabel, constraintsOne);
        constraintsOne.gridx ++;
        panelOne.add(nameTextArea, constraintsOne);
        panel.add(panelOne, constraints);
        constraints.gridy ++;

        JLabel propertyLabel = new JLabel(bundle.getString("tableProperty"));
        propertyLabel.setPreferredSize(new Dimension(width/4, 20));
        propertyLabel.setMinimumSize(new Dimension(width/4, 20));
        propertyLabel.setMaximumSize(new Dimension(width/4, 20));
        propertyTextArea = new JTextArea();
        propertyTextArea.setPreferredSize(new Dimension(width/4, 20));
        propertyTextArea.setMinimumSize(new Dimension(width/4, 20));
        propertyTextArea.setMaximumSize(new Dimension(width/4, 20));


        JPanel panelTwo = new JPanel(new GridBagLayout());
        GridBagConstraints constraintsTwo = new GridBagConstraints();
        constraintsTwo.anchor = GridBagConstraints.NORTH;
        constraintsTwo.insets = new Insets(2, 2, 2, 2);
        constraintsTwo.gridx = 0;
        constraintsTwo.gridy = 0;
        panelTwo.add(propertyLabel, constraintsTwo);
        constraintsTwo.gridx ++;
        panelTwo.add(propertyTextArea, constraintsTwo);
        panel.add(panelTwo, constraints);
        constraints.gridy ++;

        JPanel panelDets = new JPanel(new GridBagLayout());
        GridBagConstraints constraintsDets = new GridBagConstraints();
        constraintsDets.anchor = GridBagConstraints.NORTH;
        constraintsDets.insets = new Insets(2, 2, 2, 2);
        constraintsDets.gridx = 0;
        constraintsDets.gridy = 0;

        JLabel names = new JLabel(bundle.getString("tableName"));
        names.setPreferredSize(new Dimension(width/4, 20));
        names.setMinimumSize(new Dimension(width/4, 20));
        names.setMaximumSize(new Dimension(width/4, 20));
        JLabel needDegree = new JLabel(bundle.getString("tableIsSkillNeed"));
        needDegree.setPreferredSize(new Dimension(width/4, 20));
        needDegree.setMinimumSize(new Dimension(width/4, 20));
        needDegree.setMaximumSize(new Dimension(width/4, 20));
        JLabel degreeOfBreakage = new JLabel(bundle.getString("tableDegreeOfBreakage"));
        degreeOfBreakage.setPreferredSize(new Dimension(width/4, 20));
        degreeOfBreakage.setMinimumSize(new Dimension(width/4, 20));
        degreeOfBreakage.setMaximumSize(new Dimension(width/4, 20));
        JLabel quality = new JLabel(bundle.getString("tableQuality"));
        quality.setPreferredSize(new Dimension(width/4, 20));
        quality.setMinimumSize(new Dimension(width/4, 20));
        quality.setMaximumSize(new Dimension(width/4, 20));
        panelDets.add(names, constraintsDets);
        constraintsDets.gridx ++;
        panelDets.add(needDegree, constraintsDets);
        constraintsDets.gridx ++;
        panelDets.add(degreeOfBreakage, constraintsDets);
        constraintsDets.gridx ++;
        panelDets.add(quality, constraintsDets);
        panel.add(panelDets, constraints);
        constraints.gridy ++;

        for(Details details : new Car().getDetails()){

            JPanel panelDet = new JPanel(new GridBagLayout());
            GridBagConstraints constraintsDet = new GridBagConstraints();
            constraintsDet.anchor = GridBagConstraints.NORTH;
            constraintsDet.insets = new Insets(2, 2, 2, 2);
            constraintsDet.gridx = 0;
            constraintsDet.gridy = 0;
            JLabel nameOfDetail = new JLabel(bundle.getString(details.getName()));
            nameOfDetail.setPreferredSize(new Dimension(width/4, 20));
            nameOfDetail.setMinimumSize(new Dimension(width/4, 20));
            nameOfDetail.setMaximumSize(new Dimension(width/4, 20));
            JCheckBox isSkillNeedOfDetail = new JCheckBox();
            detailsNeedSkill.add(isSkillNeedOfDetail);
            isSkillNeedOfDetail.setPreferredSize(new Dimension(width/4, 20));
            isSkillNeedOfDetail.setMinimumSize(new Dimension(width/4, 20));
            isSkillNeedOfDetail.setMaximumSize(new Dimension(width/4, 20));
            JTextArea degreeOfBreakageOfDetail = new JTextArea();
            detailsDegree.add(degreeOfBreakageOfDetail);
            degreeOfBreakageOfDetail.setPreferredSize(new Dimension(width/4, 20));
            degreeOfBreakageOfDetail.setMinimumSize(new Dimension(width/4, 20));
            degreeOfBreakageOfDetail.setMaximumSize(new Dimension(width/4, 20));
            JSlider qualityOfDetail = new JSlider();
            detailsQuality.add(qualityOfDetail);
            qualityOfDetail.setPreferredSize(new Dimension(width/4, 20));
            qualityOfDetail.setMinimumSize(new Dimension(width/4, 20));
            qualityOfDetail.setMaximumSize(new Dimension(width/4, 20));
            constraintsDet.gridx ++;
            panelDet.add(nameOfDetail, constraintsDet);
            constraintsDet.gridx ++;
            panelDet.add(isSkillNeedOfDetail, constraintsDet);
            constraintsDet.gridx ++;
            panelDet.add(degreeOfBreakageOfDetail, constraintsDet);
            constraintsDet.gridx ++;
            panelDet.add(qualityOfDetail, constraintsDet);
            panel.add(panelDet, constraints);
            constraints.gridy ++;
        }

        JButton send = new JButton(bundle.getString("send"));
        send.addActionListener(e -> {
            Client.write("insert" + toJson());
            Thread thread = new Thread(() -> {
                String wayIn = null;
                wayIn = Client.read();
                System.out.println(wayIn);
            });
            thread.start();
        });
        panel.add(send, constraints);

        add(panel);
    }

    public CarWindow setIsVisible(boolean visible){
        if(visible)
            drawWindow();
        setVisible(visible);
        this.visible = visible;
        return this;
    }

    public boolean getVisible(){
        return visible;
    }

    private String toJson(){
        String json = "{";

        json += nameTextArea.getText() + ",";
        json += propertyTextArea.getText() + ",";

        for(int i = 0; i < 7; i++){
            json += detailsNeedSkill.get(i).isSelected() + ",";
            json += detailsDegree.get(i).getText() + ",";
            if(i == 6)
                json += detailsQuality.get(i).getValue();
            else
                json += detailsQuality.get(i).getValue() + ",";
        }

        json += "}";
        return json;
    }
}
