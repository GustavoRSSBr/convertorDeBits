/*
 *@author:Gustavo Rodrigues Santos Silva
 * RA: 1110481922011
 */
package br.com.gustavorssbr.calcularbits;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private EditText etBits;
    private RadioButton rbByte;
    private RadioButton rbKB;
    private RadioButton rbMB;
    private RadioButton rbGB;
    private RadioButton rbTB;
    private TextView tvSaida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etBits = findViewById(R.id.etBits);
        rbByte = findViewById(R.id.rbByte);
        rbByte.setChecked(true);
        rbKB = findViewById(R.id.rbKB);
        rbMB = findViewById(R.id.rbMB);
        rbGB = findViewById(R.id.rbGB);
        rbTB = findViewById(R.id.rbTB);

        Button btnConverter = findViewById(R.id.btnConverter);

        tvSaida = findViewById(R.id.tvSaida);

        btnConverter.setOnClickListener(op -> gerar());
    }

    private void gerar() {
        StringBuilder buffer = new StringBuilder();
        String input = etBits.getText().toString();

        // Verifica se o campo de entrada não está vazio
        if (input.isEmpty()) {
            tvSaida.setText("Por favor, insira um valor válido.");
            return;
        }

        try {
            BigDecimal qtdBits = new BigDecimal(input);

            if (rbByte.isChecked()) {
                buffer.append(qtdBits.divide(BigDecimal.valueOf(8)));
            } else if (rbKB.isChecked()) {
                buffer.append(qtdBits.divide(BigDecimal.valueOf(8 * 1024)));
            } else if (rbMB.isChecked()) {
                buffer.append(qtdBits.divide(BigDecimal.valueOf(8 * 1024 * 1024)));
            } else if (rbGB.isChecked()) {
                buffer.append(qtdBits.divide(BigDecimal.valueOf(8L * 1024 * 1024 * 1024)));
            } else if (rbTB.isChecked()) {
                buffer.append(qtdBits.divide(BigDecimal.valueOf(8L * 1024 * 1024 * 1024 * 1024)));
            }

            tvSaida.setText(buffer.toString());
        } catch (NumberFormatException e) {
            tvSaida.setText("Erro: valor inválido.");
        } catch (ArithmeticException e) {
            tvSaida.setText("Erro: operação aritmética inválida.");
        }
    }


}