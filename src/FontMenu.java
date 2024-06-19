import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FontMenu extends JDialog {
    private NotepadGUI source;

    private JTextField currentFontField, currentFontStyleField, currentFontSizeField;
    private JPanel currentColorBox;

    public FontMenu(NotepadGUI source){
        this.source = source;
        setTitle("Font Settings");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(425, 350);
        setLocationRelativeTo(source);
        setModal(true);

        setLayout(null);

        addMenuComponents();
    }

    private void addMenuComponents(){
        addFontChooser();
        addFontStyleChooser();
        addFontSizeChooser();
        addFontColorChooser();

        JButton applyButton = new JButton("Apply");
        applyButton.setBounds(230, 265, 75, 25);
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fontType  = currentFontField.getText();

                int fontStyle;
                switch(currentFontStyleField.getText()){
                    case "Plain":
                        fontStyle = Font.PLAIN;
                        break;
                    case "Bold":
                        fontStyle = Font.BOLD;
                        break;
                    case "Italic":
                        fontStyle = Font.ITALIC;
                        break;
                    default:
                        fontStyle = Font.BOLD | Font.ITALIC;
                        break;
                }

                int fontSize = Integer.parseInt(currentFontSizeField.getText());

                Color fontColor = currentColorBox.getBackground();

                Font newFont = new Font(fontType, fontStyle, fontSize);

                source.getTextArea().setFont(newFont);

                source.getTextArea().setForeground(fontColor);

                FontMenu.this.dispose();
            }
        });
        add(applyButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(315, 265, 75, 25);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FontMenu.this.dispose();
            }
        });
        add(cancelButton);
    }

    private void addFontChooser(){
        JLabel fontLabel = new JLabel("Font:");
        fontLabel.setBounds(10, 5, 125, 10);
        add(fontLabel);

        JPanel fontPanel = new JPanel();
        fontPanel.setBounds(10, 15, 125, 160);

        currentFontField = new JTextField(source.getTextArea().getFont().getFontName());
        currentFontField.setPreferredSize(new Dimension(125, 25));
        currentFontField.setEditable(false);
        fontPanel.add(currentFontField);

        JPanel listOfFontsPanel = new JPanel();

        listOfFontsPanel.setLayout(new BoxLayout(listOfFontsPanel, BoxLayout.Y_AXIS));

        listOfFontsPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(listOfFontsPanel);
        scrollPane.setPreferredSize(new Dimension(125, 125));

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();

        for(String fontName : fontNames){
            JLabel fontNameLabel = new JLabel(fontName);

            fontNameLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    currentFontField.setText(fontName);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    fontNameLabel.setOpaque(true);
                    fontNameLabel.setBackground(Color.BLUE);
                    fontNameLabel.setForeground(Color.WHITE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    fontNameLabel.setBackground(null);
                    fontNameLabel.setForeground(null);
                }
            });

            listOfFontsPanel.add(fontNameLabel);
        }
        fontPanel.add(scrollPane);

        add(fontPanel);
    }

    private void addFontStyleChooser(){
        JLabel fontStyleLabel = new JLabel("Font Style:");
        fontStyleLabel.setBounds(145, 5, 125, 10);
        add(fontStyleLabel);

        JPanel fontStylePanel = new JPanel();
        fontStylePanel.setBounds(145, 15, 125, 160);

        int currentFontStyle = source.getTextArea().getFont().getStyle();
        String currentFontStyleText;

        switch(currentFontStyle){
            case Font.PLAIN:
                currentFontStyleText = "Plain";
                break;
            case Font.BOLD:
                currentFontStyleText = "Bold";
                break;
            case Font.ITALIC:
                currentFontStyleText = "Italic";
                break;
            default:
                currentFontStyleText = "Bold Italic";
                break;
        }

        currentFontStyleField = new JTextField(currentFontStyleText);
        currentFontStyleField.setPreferredSize(new Dimension(125, 25));
        currentFontStyleField.setEditable(false);
        fontStylePanel.add(currentFontStyleField);

        JPanel listOfFontStylesPanel = new JPanel();

        listOfFontStylesPanel.setLayout(new BoxLayout(listOfFontStylesPanel, BoxLayout.Y_AXIS));
        listOfFontStylesPanel.setBackground(Color.WHITE);

        JLabel plainStyle = new JLabel("Plain");
        plainStyle.setFont(new Font("Dialog", Font.PLAIN, 12));
        plainStyle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentFontStyleField.setText(plainStyle.getText());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                plainStyle.setOpaque(true);
                plainStyle.setBackground(Color.BLUE);
                plainStyle.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                plainStyle.setBackground(null);
                plainStyle.setForeground(null);
            }
        });
        listOfFontStylesPanel.add(plainStyle);

        JLabel boldStyle = new JLabel("Bold");
        boldStyle.setFont(new Font("Dialog", Font.BOLD, 12));
        boldStyle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentFontStyleField.setText(boldStyle.getText());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                boldStyle.setOpaque(true);
                boldStyle.setBackground(Color.BLUE);
                boldStyle.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boldStyle.setBackground(null);
                boldStyle.setForeground(null);
            }
        });
        listOfFontStylesPanel.add(boldStyle);

        JLabel italicStyle = new JLabel("Italic");
        italicStyle.setFont(new Font("Dialog", Font.ITALIC, 12));
        italicStyle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentFontStyleField.setText(italicStyle.getText());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                italicStyle.setOpaque(true);
                italicStyle.setBackground(Color.BLUE);
                italicStyle.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                italicStyle.setBackground(null);
                italicStyle.setForeground(null);
            }
        });
        listOfFontStylesPanel.add(italicStyle);

        JLabel boldItalicStyle = new JLabel("Bold Italic");
        boldItalicStyle.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
        boldItalicStyle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentFontStyleField.setText(boldItalicStyle.getText());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                boldItalicStyle.setOpaque(true);
                boldItalicStyle.setBackground(Color.BLUE);
                boldItalicStyle.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boldItalicStyle.setBackground(null);
                boldItalicStyle.setForeground(null);
            }
        });
        listOfFontStylesPanel.add(boldItalicStyle);

        JScrollPane scrollPane = new JScrollPane(listOfFontStylesPanel);
        scrollPane.setPreferredSize(new Dimension(125,125));
        fontStylePanel.add(scrollPane);

        add(fontStylePanel);
    }

    private void addFontSizeChooser(){
        JLabel fontSizeLabel= new JLabel("Font Size: ");
        fontSizeLabel.setBounds(275, 5, 125, 10);
        add(fontSizeLabel);

        JPanel fontSizePanel = new JPanel();
        fontSizePanel.setBounds(275, 15, 125, 160);

        currentFontSizeField = new JTextField(
                Integer.toString(source.getTextArea().getFont().getSize())
        );
        currentFontSizeField.setPreferredSize(new Dimension(125, 25));
        currentFontSizeField.setEditable(false);
        fontSizePanel.add(currentFontSizeField);


        JPanel listOfFontSizesPanel = new JPanel();
        listOfFontSizesPanel.setLayout(new BoxLayout(listOfFontSizesPanel, BoxLayout.Y_AXIS));
        listOfFontSizesPanel.setBackground(Color.WHITE);

        for(int i = 8; i <= 72; i+= 2){
            JLabel fontSizeValueLabel = new JLabel(Integer.toString(i));
            fontSizeValueLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    currentFontSizeField.setText(fontSizeValueLabel.getText());
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    fontSizeValueLabel.setOpaque(true);
                    fontSizeValueLabel.setBackground(Color.BLUE);
                    fontSizeValueLabel.setForeground(Color.WHITE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    fontSizeValueLabel.setBackground(null);
                    fontSizeValueLabel.setForeground(null);
                }
            });

            listOfFontSizesPanel.add(fontSizeValueLabel);
        }
        JScrollPane scrollPane = new JScrollPane(listOfFontSizesPanel);
        scrollPane.setPreferredSize(new Dimension(125, 125));
        fontSizePanel.add(scrollPane);

        add(fontSizePanel);
    }

    private void addFontColorChooser(){
        currentColorBox = new JPanel();
        currentColorBox.setBounds(175, 200, 23, 23);
        currentColorBox.setBackground(source.getTextArea().getForeground());
        currentColorBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(currentColorBox);

        JButton chooseColorButton = new JButton("Choose Color");
        chooseColorButton.setBounds(10, 200, 150, 25);
        chooseColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color c = JColorChooser.showDialog(FontMenu.this, "Select a color", Color.BLACK);

                currentColorBox.setBackground(c);
            }
        });
        add(chooseColorButton);
    }
}











