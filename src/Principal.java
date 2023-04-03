import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Principal extends JFrame implements ActionListener {

    private JTextField campo;
    private float valor1 = 0,valor2 = 0,resultado = 0;
    private String operacionElegida = "";

    Principal(){
        setTitle("Calculadora");
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        setSize(210,235);
        setMinimumSize(new Dimension(200,235));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        campo = new JTextField();
        campo.setEditable(false);
        campo.setFont(new Font("sans-serif",Font.BOLD,22));
        campo.setForeground(Color.BLACK);

        JPanel jPrincipal = new JPanel();
        jPrincipal.setLayout(new GridLayout(5,4));

        JButton num0 = createButton("0");
        JButton num1 = createButton("1");
        JButton num2 = createButton("2");
        JButton num3 = createButton("3");
        JButton num4 = createButton("4");
        JButton num5 = createButton("5");
        JButton num6 = createButton("6");
        JButton num7 = createButton("7");
        JButton num8 = createButton("8");
        JButton num9 = createButton("9");

        JButton btSum = createButton("+");
        JButton btRes = createButton("-");
        JButton btMulti = createButton("*");
        JButton btEqual = createButton("=");
        JButton btDivi = createButton("/");

        JButton btComa = createButton(",");
        JButton changeSign = createButton("+/-");

        JButton btDelte = createButton("C");
        JButton btCleanCampo = createButton("CE");
        JButton btDeleteNumber = createButton("DEL");

        jPrincipal.add(btCleanCampo);
        jPrincipal.add(btDelte);
        jPrincipal.add(btDeleteNumber);
        jPrincipal.add(btDivi);

        jPrincipal.add(num7);
        jPrincipal.add(num8);
        jPrincipal.add(num9);
        jPrincipal.add(btMulti);

        jPrincipal.add(num4);
        jPrincipal.add(num5);
        jPrincipal.add(num6);
        jPrincipal.add(btRes);

        jPrincipal.add(num1);
        jPrincipal.add(num2);
        jPrincipal.add(num3);
        jPrincipal.add(btSum);

        jPrincipal.add(changeSign);
        jPrincipal.add(num0);
        jPrincipal.add(btComa);
        jPrincipal.add(btEqual);

        btComa.setEnabled(false);

        //JFrame
        add(campo);
        add(jPrincipal);
    }

    public static void main(String[] args) {
        new Principal().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("0")){
            setNumCampo(0);
        }
        if(e.getActionCommand().equals("1")){
            setNumCampo(1);
        }
        if(e.getActionCommand().equals("2")){
            setNumCampo(2);
        }
        if(e.getActionCommand().equals("3")){
            setNumCampo(3);
        }
        if(e.getActionCommand().equals("4")){
            setNumCampo(4);
        }
        if(e.getActionCommand().equals("5")){
            setNumCampo(5);
        }
        if(e.getActionCommand().equals("6")){
            setNumCampo(6);
        }
        if(e.getActionCommand().equals("7")){
            setNumCampo(7);
        }
        if(e.getActionCommand().equals("8")){
            setNumCampo(8);
        }
        if(e.getActionCommand().equals("9")){
            setNumCampo(9);
        }
        if(e.getActionCommand().equals("+")){
            operation(e.getActionCommand());
        }
        if(e.getActionCommand().equals("-")){
            operation(e.getActionCommand());
        }
        if(e.getActionCommand().equals("*")){
            operation(e.getActionCommand());
        }
        if(e.getActionCommand().equals("/")){
            operation(e.getActionCommand());
        }
        if(e.getActionCommand().equals("=")){
            if(!operacionElegida.equals(""))
                operation(operacionElegida);
        }
        if(e.getActionCommand().equals("C")){
            cleanVariables();
            cleanCampo();
        }
        if(e.getActionCommand().equals("CE")){
            cleanCampo();
        }
        if(e.getActionCommand().equals("del")){
            campo.setText(deleteANumber());
        }
        if(e.getActionCommand().equals("+/-")){
            if(!campo.getText().equals("")){
                Float valor = Float.parseFloat(campo.getText()) * -1;
                campo.setText(String.valueOf(valor));
            }

        }
    }

    private JButton createButton(String text){
        JButton button = new JButton(text);
        button.addActionListener(this);
        button.setActionCommand(text);
        button.setBackground(Color.WHITE);
        button.setMargin(new Insets(0,10,0,10));
        return button;
    }
    private void setNumCampo(int number){

        String getCampo = campo.getText();
        getCampo += number;
        campo.setText(getCampo);
    }
    private void cleanCampo(){
        campo.setText("");
    }
    private void operation(String operacion){
        if(valor1 == 0)
            valor1 = Float.parseFloat(campo.getText());
        else if(valor2 == 0)
            valor2 = Float.parseFloat(campo.getText());

        operacionElegida = operacion;

        switch (operacion){
            case "+":
                resultado = valor1 + valor2;
                mostrarResultado();
                mostrar();
                verificacionFloat();
                break;
            case "-":
                resultado = valor1 - valor2;
                mostrar();
                mostrarResultado();
                verificacionFloat();
                break;
            case "*":
                resultado = valor1 * valor2;
                mostrar();
                campo.setText(String.valueOf(resultado));
                verificacionFloat();
                break;
            case "/":
                resultado = valor1 / valor2;
                mostrar();
                mostrarResultado();
                verificacionFloat();
                break;
        }
    }
    private void mostrarResultado(){
        campo.setText(String.valueOf(resultado));
    }
    private void mostrar(){
        System.out.print(valor1 + " ");
        System.out.print(operacionElegida + " ");
        System.out.print(valor2 + " = ");
        System.out.println(resultado);
    }
    private void verificacionFloat(){
        if(valor1 != 0 && valor2 != 0){
            valor1 = resultado;
            valor2 = 0;
        }
    }
    private void cleanVariables(){
        valor1 = 0;
        valor2 = 0;
        resultado = 0;
        operacionElegida = "";
    }
    private String deleteANumber(){
        String campoTexto = campo.getText();
        if(!campoTexto.isEmpty()){
            CharSequence charSequence = campoTexto.subSequence(0,campoTexto.length()-1);
            return charSequence.toString();
        }
        return "";
    }
}
