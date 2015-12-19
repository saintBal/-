package ru.levelp.quadraticEquation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main implements ActionListener {

    private JFrame window;
    private JPanel mainPanel, inputPanel, outputPanel, buttonPanel;
    private JButton jbtnAdd, jbtnReset, jbtnExit;
    private JLabel jlblOut, jlblNum1Caption, jlblNum2Caption, jlblNum3Caption;
    private JTextField jtxtNum1, jtxtNum2, jtxtNum3;

    //конструктор
    public Main() {
        //создать фрейм
        window = new JFrame("Решение квадратного уравнения");

        //по закрытию формы - закрывать приложение
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //установить запрет на расширение окна
        window.setResizable(false);

        //создать контейнеры - панели
        mainPanel = new JPanel(new BorderLayout());
        inputPanel = new JPanel();
        outputPanel = new JPanel();
        buttonPanel = new JPanel();

        //установить менеджера расположения для панелей
        inputPanel.setLayout(new GridLayout(1, 4));    //1 строка и 4 столбца
        outputPanel.setLayout(new GridLayout(1, 1));    //1 строка и 1 столбец
        buttonPanel.setLayout(new GridLayout(1, 10, 50, 10)); //1 строка и 4 столбца; расстояние между компонентами будет равно 5 (чтобы кнопки не слипались)

        //добавить панели на главную панель
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(outputPanel, BorderLayout.SOUTH);

        //добавить главную панель на фрейм
        window.getContentPane().add(mainPanel);

        //добавить кнопки и текстовые поля на панели
        addButtonsAndTextFields();

        //установить необходимый размер фрейма и компонентов
        window.pack();
        window.setLocationRelativeTo(null); // для появления фрейма в центре экрана

        //отобразить фрейм - главное окно программы
        window.setVisible(true);
    }


    //добавление кнопко и текстовых полей на панели
    private void addButtonsAndTextFields() {

        //создать кнопки
        jbtnAdd = new JButton("Сложить");
        jbtnReset = new JButton("Сброс");
        jbtnExit = new JButton("Выход");

        //создать текстовые метки
        jlblOut = new JLabel(" ", JLabel.CENTER);
        jlblNum1Caption = new JLabel("Число 1:", JLabel.RIGHT);
        jlblNum2Caption = new JLabel("Число 2:", JLabel.CENTER);
        jlblNum3Caption = new JLabel("Число 3:", JLabel.LEFT);

        //создать текстовые поля
        jtxtNum1 = new JTextField();
        jtxtNum2 = new JTextField();
        jtxtNum3 = new JTextField();

        // слушатели для кнопок
        jbtnAdd.addActionListener(this);
        jbtnReset.addActionListener(this);
        jbtnExit.addActionListener(this);

        //добавить кнопки на панели
        buttonPanel.add(jbtnAdd);
        buttonPanel.add(jbtnReset);
        buttonPanel.add(jbtnExit);

        //добавить метки и текстовые поля на панели
        inputPanel.add(jlblNum1Caption);
        inputPanel.add(jtxtNum1);
        inputPanel.add(jlblNum2Caption);
        inputPanel.add(jtxtNum2);
        inputPanel.add(jlblNum3Caption);
        inputPanel.add(jtxtNum3);

        //добавить метку на панель
        outputPanel.add(jlblOut);
    }

    private static void setGUI() {
        //создать экземпляр класса Calculator
        Main gui = new Main();
    }

    //метод main - запуск программы происходит в этом методе
    public static void main(String[] args) {
        //создание компонентов в отдельном потоке
        javax.swing.SwingUtilities.invokeLater(Main::setGUI);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // определить нажатую кнопку и выполнить соотвествующую операцию
        if (event.getSource() == jbtnAdd) {
            add();
        } else if (event.getSource() == jbtnReset) {
            reset();
        } else if (event.getSource() == jbtnExit) {
            exit();
        }
    }

    // выход
    private void exit() {
        // показать пользователю сообщение для подтверждения
        int reponse = JOptionPane.showConfirmDialog(null, "Вы действительно хотите закрыть приложение?", "Подтверждение",
                JOptionPane.YES_NO_OPTION);

        //если пользователь нажал ДА
        if (reponse == JOptionPane.YES_OPTION) {

            //выйти из программы
            window.dispose();
            System.exit(0);
        }

    }

    //операции
    // сложить
    private void add() {

        double num1, num2, num3;


        //проверить введенные данные
        if ((isValidInput(jtxtNum1, "Число 1")) && (isValidInput(jtxtNum2, "Число 2")) &&
                (isValidInput(jtxtNum3, "Число 3"))) {
            //получить введенные числа
            num1 = Double.parseDouble(jtxtNum1.getText());
            num2 = Double.parseDouble(jtxtNum2.getText());
            num3 = Double.parseDouble(jtxtNum3.getText());

            //сделать расчет
            double disk = Math.pow(num2, 2) - 4 * num1 * num3;
            if (disk < 0) {
                jlblOut.setText("Дискреминант меньше нуля. Корней нет.");
            } else if (disk == 0) {
                double x = -num2 / (2 * num1);
                String s = String.format("%g =%f", x);
                jlblOut.setText(s);
            } else {
                double x3 = Math.sqrt(disk);
                double x1 = (-num2 + x3) / (2 * num1);
                double x2 = (-num2 - x3) / (2 * num1);
                String s = String.format("%g %g =%f", x1, x2);
                jlblOut.setText(s);
            }
        }
    }

    //очищение данных в текстовых полях
    private void reset() {
        jlblOut.setText(" ");
        jtxtNum1.setText("");
        jtxtNum2.setText("");
        jtxtNum3.setText("");
        jtxtNum1.requestFocus();

    }

    //проверка данных, введенных пользователем
    private boolean isValidInput(JTextField jtxt, String description) {

        //если был введен какой-либо текст
        if (jtxt.getText().trim().length() > 0) {
            //проверка на ввод только целого числа
            try {
                //попытка преобразовать текст в целое число
                double num = Double.parseDouble(jtxt.getText());

                //если все нормально - возвращаем true
                return true;

            } catch (NumberFormatException e) {

                //предупреждение - неверный формат числа
                JOptionPane.showMessageDialog(window, "Вы должны ввести целое число!", "Ошибка", JOptionPane.WARNING_MESSAGE);

                //расположить курсор в текстово окне, чтобы пользователь еще раз ввел число
                jtxt.requestFocus();
                jtxt.selectAll();

                //ошибка - возвращаем false
                return false;
            }

        } else {// если пользователь не ввели никаких данных

            //предупреждение, что нужно ввести данные
            JOptionPane.showMessageDialog(window, "Введите число " + description, "Ошибка", JOptionPane.WARNING_MESSAGE);

            //расположить курсор в текстово окне, чтобы пользователь еще раз ввел число
            jtxt.requestFocus();
            jtxt.selectAll();

            //ошибка - возвращаем false
            return false;
        }

    }
}
