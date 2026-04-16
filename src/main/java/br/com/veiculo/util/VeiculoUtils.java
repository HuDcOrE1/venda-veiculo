package br.com.veiculo.util;

public class VeiculoUtils {
    public static Double converterValor(String valor) {
        if (valor == null || valor.isBlank()) return null;

        return Double.parseDouble(
                valor
                        .replace("R$", "")
                        .replace(".", "")
                        .replace(",", ".")
                        .trim()
        );
    }
}
