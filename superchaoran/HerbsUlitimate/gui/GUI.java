package superchaoran.HerbsUlitimate.gui;

import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Constants;
import superchaoran.HerbsUlitimate.HerbsUltimateMain;
import superchaoran.HerbsUlitimate.constants.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class GUI extends JFrame implements ItemListener{

    private JPanel contentPane;
    private JComboBox<Herb> herbJComboBox;
    private JComboBox<UnfPotion> unfPotionJComboBox;
    private ButtonGroup group;
    private JLabel unitProfitJLabel;
    private JLabel loadingJLabel;

    public GUI(final HerbsUltimateMain script, final ClientContext ctx) {
        Tile loc = ctx.players.local().tile();
        setTitle("Herb Ultimate");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 350, 250);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Herb Ultimate");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(60, 10, 200, 30);
        contentPane.add(lblNewLabel);

        JLabel loadingJLabel = new JLabel("Realtime Price/Profit are still loading...");
        loadingJLabel.setBounds(15, 35,300, 20);
        loadingJLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        contentPane.add(loadingJLabel);

        int methodLen = Method.values().length;
        JRadioButton[] methodRadioButtons = new JRadioButton[methodLen];


        group = new ButtonGroup();
        JPanel radioPanel = new JPanel(new GridLayout(1, 0));
        for (int i = 0; i < methodLen; i++) {
            methodRadioButtons[i] = new JRadioButton(Method.values()[i].name());
            methodRadioButtons[i].addItemListener(this);
            methodRadioButtons[i].setActionCommand(Method.values()[i].name());
            radioPanel.add(methodRadioButtons[i]);
            group.add(methodRadioButtons[i]);
        }
        radioPanel.setBounds(50,65,300,20);
        contentPane.add(radioPanel, BorderLayout.LINE_START);

        herbJComboBox = new JComboBox<Herb>(Herb.values());
        herbJComboBox.setBounds(65, 95, 200, 50);
        contentPane.add(herbJComboBox);
        herbJComboBox.setVisible(false);

//        ItemListener herbPanelListener = new ItemListener() {//add actionlistner to listen for change
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Herb selectedHerb = (Herb)(herbJComboBox.getSelectedItem());
//                unitProfitJLabel= new JLabel("Unit Profit: " + selectedHerb.getUnitProfit());
//                unitProfitJLabel.setBounds(225, 80, 100, 50);
//                contentPane.add(unitProfitJLabel);
//
//            }
//        };


        unfPotionJComboBox = new JComboBox<UnfPotion>(UnfPotion.values());
        unfPotionJComboBox.setBounds(65, 95, 200, 50);
        contentPane.add(unfPotionJComboBox);
        unfPotionJComboBox.setVisible(false);

//        ActionListener UnfPanelActionListener = new ActionListener() {//add actionlistner to listen for change
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                UnfPotion selectedUnfPotion = (UnfPotion) unfPotionJComboBox.getSelectedItem();
//                unitProfitJLabel= new JLabel("Unit Profit: " + selectedUnfPotion.getUnitProfit());
//                unitProfitJLabel.setBounds(225, 80, 100, 50);
//                contentPane.add(unitProfitJLabel);
//            }
//        };


        JButton btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ctx.game.clientState() == Constants.GAME_MAP_LOADED) {
                    if(group.getSelection().getActionCommand().equals(Method.CleanHerb.name())){
                        script.submit(new MethodChosen(Method.CleanHerb, (Herb)herbJComboBox.getSelectedItem(), null));
                    } else {
                        script.submit(new MethodChosen(Method.MakeUnfPotion, null, (UnfPotion)unfPotionJComboBox.getSelectedItem()));
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Please start logged in.", "", JOptionPane.WARNING_MESSAGE);
                    ctx.controller.stop();
                    dispose();
                }
            }
        });

        JLabel lblLogType = new JLabel("Type:");
        lblLogType.setBounds(20, 105, 46, 28);
        contentPane.add(lblLogType);

        btnStart.setBounds(100, 165, 115, 30);
        contentPane.add(btnStart);
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            ButtonModel b = group.getSelection();
            if(b.getActionCommand().equals(Method.CleanHerb.name())){
                unfPotionJComboBox.setVisible(false);
                herbJComboBox.setVisible(true);
                contentPane.repaint();
            } else {
                herbJComboBox.setVisible(false);
                unfPotionJComboBox.setVisible(true);
                contentPane.repaint();
            }
        }
        else if (e.getStateChange() == ItemEvent.DESELECTED) {
            // Your deselected code here.
        }
    }

    public void setLoadingJLabel(String text){
        this.loadingJLabel.setText(text);
        repaint();
    }

    public JLabel getLoadingJLabel(){
        return this.loadingJLabel;
    }

    public JPanel getContentPane(){
        return contentPane;
    }

}
