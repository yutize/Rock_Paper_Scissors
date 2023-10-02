import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame implements ActionListener {
    private JButton rockButton, paperButton, scissorsButton, quitButton;
    private JLabel playerWinsLabel, computerWinsLabel, tiesLabel;
    private JTextArea resultsTextArea;
    private int playerWins, computerWins, ties;
    private Random random;

    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(BorderFactory.createTitledBorder("Pick an action!"));
        rockButton = new JButton("Rock", new ImageIcon("rock.jpg"));
        paperButton = new JButton("Paper", new ImageIcon("pap.jpg"));
        scissorsButton = new JButton("Scissors", new ImageIcon("sci.jpg"));
        quitButton = new JButton("Quit");
        rockButton.addActionListener(this);
        paperButton.addActionListener(this);
        scissorsButton.addActionListener(this);
        quitButton.addActionListener(this);
        buttonsPanel.add(rockButton);
        buttonsPanel.add(paperButton);
        buttonsPanel.add(scissorsButton);
        buttonsPanel.add(quitButton);
        JPanel statsPanel = new JPanel(new GridLayout(3, 2));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Statistics"));
        playerWinsLabel = new JLabel("Player Wins:");
        computerWinsLabel = new JLabel("Computer Wins:");
        tiesLabel = new JLabel("Ties:");
        statsPanel.add(playerWinsLabel);
        statsPanel.add(computerWinsLabel);
        statsPanel.add(tiesLabel);

        JPanel resultsPanel = new JPanel();
        resultsPanel.setBorder(BorderFactory.createTitledBorder("Results"));
        resultsTextArea = new JTextArea(10, 30);
        resultsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsTextArea);
        resultsPanel.add(scrollPane);

        add(buttonsPanel, BorderLayout.NORTH);
        add(statsPanel, BorderLayout.CENTER);
        add(resultsPanel, BorderLayout.SOUTH);

        playerWins = 0;
        computerWins = 0;
        ties = 0;
        random = new Random();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quitButton) {
            System.exit(0);
        } else {
            String playerChoice = e.getActionCommand();

            String[] choices = {"Rock", "Paper", "Scissors"};
            String computerChoice = choices[random.nextInt(choices.length)];

            String result = determineWinner(playerChoice, computerChoice);

            updateStats(result);
            updateResults(result);
        }
    }

    private String determineWinner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            return "Tie";
        } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            return "Player";
        } else {
            return "Computer";
        }
    }

    private void updateStats(String result) {
        if (result.equals("Player")) {
            playerWins++;
        } else if (result.equals("Computer")) {
            computerWins++;
        } else {
            ties++;
        }

        playerWinsLabel.setText("Player beats computer!" + playerWins);
        computerWinsLabel.setText("Computer beats player!" + computerWins);
        tiesLabel.setText("Its a tie!" + ties);
    }

    private void updateResults(String result) {
        String outcome = result.equals("Tie") ? "Tie" : result + " wins";
        resultsTextArea.append(result + " - " + outcome + "\n");
    }
}
