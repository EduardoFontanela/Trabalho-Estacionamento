import java.util.ArrayList;

public class Estacionamento {
    ArrayList<Vaga> vagas = new ArrayList<>();
    ArrayList<Veiculo> veiculosEstacionados = new ArrayList<>();
    ArrayList<Registro> registros = new ArrayList<>();

    public void cadastrarVaga(int numero, String tamanho) {
        vagas.add(new Vaga(numero, tamanho));
        System.out.println("Vaga cadastrada com sucesso.");
    }

    public void registrarEntrada(String placa, String modelo, String tamanho) {
        for (Vaga vaga : vagas) {
            if (vaga.disponivel && vaga.tamanho.equalsIgnoreCase(tamanho)) {
                long horaEntrada = System.currentTimeMillis();
                Veiculo veiculo = new Veiculo(placa, modelo, tamanho, horaEntrada);
                vaga.ocupar();
                veiculosEstacionados.add(veiculo);
                System.out.println("Entrada registrada para o veículo com placa " + placa + " na vaga " + vaga.numero);
                return;
            }
        }
        System.out.println("Nenhuma vaga disponível para o tamanho do veículo.");
    }

    public void registrarSaidaComHoras(String placa, long horaEntrada, long horaSaida, long horasPermanencia) {
        Veiculo veiculo = buscarVeiculoEstacionado(placa);
        if (veiculo != null) {
            Vaga vaga = buscarVagaPorPlaca(placa);
            if (vaga != null) {
                vaga.liberar();
                veiculo.horaSaida = horaSaida;
                double valorPago = calcularValorPagoPorHoras(horasPermanencia);
                registros.add(new Registro(placa, horaEntrada, horaSaida, valorPago));
                veiculosEstacionados.remove(veiculo);
                System.out.println("Saída registrada para o veículo com placa " + placa + ". Valor a pagar: R$ " + valorPago);
            }
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    public double calcularValorPagoPorHoras(long horasPermanencia) {
        if (horasPermanencia <= 1) {
            return 5.0;
        } else if (horasPermanencia <= 3) {
            return 10.0;
        } else {
            return 15.0;
        }
    }

    public Vaga buscarVagaPorPlaca(String placa) {
        for (Veiculo veiculo : veiculosEstacionados) {
            if (veiculo.placa.equals(placa)) {
                for (Vaga vaga : vagas) {
                    if (!vaga.disponivel) {
                        return vaga;
                    }
                }
            }
        }
        return null;
    }

    public Veiculo buscarVeiculoEstacionado(String placa) {
        for (Veiculo veiculo : veiculosEstacionados) {
            if (veiculo.placa.equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    public void gerarRelatorio() {
        System.out.println("Vagas ocupadas no momento:");
        for (Vaga vaga : vagas) {
            if (!vaga.disponivel) {
                Veiculo veiculo = buscarVeiculoEstacionadoPorVaga(vaga);
                if (veiculo != null) {
                    System.out.println("Vaga: " + vaga.numero + " | Tamanho: " + vaga.tamanho + " | Placa: " + veiculo.placa);
                }
            }
        }
    }

    public void gerarHistorico() {
        System.out.println("Histórico de permanência:");
        for (Registro registro : registros) {
            System.out.println("Placa: " + registro.placa + " | Entrada: " + registro.horaEntrada +
                    " | Saída: " + registro.horaSaida + " | Valor pago: R$ " + registro.valorPago);
        }
    }

    public void listarVagasDisponiveis() {
        System.out.println("Vagas disponíveis no momento:");
        for (Vaga vaga : vagas) {
            if (vaga.disponivel) {
                System.out.println("Vaga: " + vaga.numero + " | Tamanho: " + vaga.tamanho);
            }
        }
    }

    private Veiculo buscarVeiculoEstacionadoPorVaga(Vaga vaga) {
        for (Veiculo veiculo : veiculosEstacionados) {
            if (buscarVagaPorPlaca(veiculo.placa) == vaga) {
                return veiculo;
            }
        }
        return null;
    }
}
