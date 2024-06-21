import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GerenciamentoDoacoes extends JFrame {
    private JTextField tipoField;
    private JTextField quantidadeField;
    private JTextField dataField;
    private JLabel totalLabel;

    public GerenciamentoDoacoes() {
        setTitle("Sistema de Gerenciamento de Doações");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

        panel.add(new JLabel("Tipo de Doação:"));
        tipoField = new JTextField();
        panel.add(tipoField);

        panel.add(new JLabel("Quantidade:"));
        quantidadeField = new JTextField();
        panel.add(quantidadeField);

        panel.add(new JLabel("Data (YYYY-MM-DD):"));
        dataField = new JTextField();
        panel.add(dataField);

        JButton adicionarButton = new JButton("Adicionar Doação");
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarDoacao();
            }
        });
        panel.add(adicionarButton);

        totalLabel = new JLabel("Total de Doações: 0");
        panel.add(totalLabel);

        add(panel, BorderLayout.CENTER);
        atualizarTotalDoacoes();
    }

    private void adicionarDoacao() {
        String tipo = tipoField.getText();
        int quantidade;
        String data = dataField.getText();

        try {
            quantidade = Integer.parseInt(quantidadeField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantidade deve ser um número inteiro!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Doacao doacao = new Doacao(tipo, quantidade, data);
        DatabaseHelper.adicionarDoacao(doacao);

        tipoField.setText("");
        quantidadeField.setText("");
        dataField.setText("");

        JOptionPane.showMessageDialog(this, "Doação adicionada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        atualizarTotalDoacoes();
    }

    private void atualizarTotalDoacoes() {
        int total = DatabaseHelper.calcularTotalDoacoes();
        totalLabel.setText("Total de Doações: " + total);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DatabaseHelper.criarTabela();
                new GerenciamentoDoacoes().setVisible(true);
            }
        });
    }
}
