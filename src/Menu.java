import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu //extends JPanel
{
    JLabel label = new JLabel();
    private JButton playButton = new JButton();
    private JButton menuButton = new JButton();

    public Menu()
    {
        JButton _playButton = new JButton();
        JButton _menuButton = new JButton();

        _playButton.setText(" Play ");// set button that was passed in.
        _playButton.setFont(new Font( "Menu", Font.PLAIN |Font.BOLD, 40));
        _playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main._InPlay = true;

            } } );

        _menuButton.setText(" Settings ");// set button that was passed in.
        _menuButton.setFont(new Font( "Menu",Font.ITALIC | Font.BOLD, 25));
        _menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main._InPlay = true;

            } } );

        this.playButton =_playButton;
        this.menuButton =_menuButton;
    }

    public JButton get_playButton() { return playButton; }
    public JButton get__menuButton() { return menuButton; }

}
