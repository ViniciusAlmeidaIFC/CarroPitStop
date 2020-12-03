import java.util.Random;

public class CarroCorridaThread extends Thread {

	Random random = new Random();
	String nome;					// nome do carro
	int distanciaCorrida = 0;		// distância já corrida pelo carro
	int distanciaTotalCorrida;	// distância a ser corrida pelo carro
	int andar = 0;					// distancia do carro em m
	int percorrido = 0;					// quantidades de metros corridos na corrida
	static int colocacao = 0;		// colocação do carro ao final da corrida
	final static int MAXIMO_PERCORRER = 50; // distância máxima em metros que um carro pode correr
	int numero = random.nextInt(1);

	/** Construtor da classe. Parâmtros : Nome do Carro e Distância da Corrida */
	public CarroCorridaThread (String nome, int distanciaTotalCorrida) {
		/* chamando o construtor de Thread passando o nome do carro como parâmetro */
		super(nome);
		this.distanciaTotalCorrida = distanciaTotalCorrida;
		this.nome = nome;
	}
	/** Imprime o último andar do carro e a distância percorrida */
	public void carroImprimindoSituacao () {
		System.out.println("O " + nome +  " andou " + andar + "m \t e já percorreu " +
							distanciaCorrida + "m" + " faltam "+(distanciaTotalCorrida - distanciaCorrida)+"m a serem percorridos! " );
	}
	/** Faz o carro andar */
	public void carroAnda() {
		percorrido++;
		andar = (int) (Math.random() * MAXIMO_PERCORRER);
		distanciaCorrida += andar;
		if (distanciaCorrida > distanciaTotalCorrida) {
			distanciaCorrida = distanciaTotalCorrida;
		}
	}
	/** Imprime a colocação do carro ao final da corrida */
	public void colocacaoCarro () {
		colocacao++;
		System.out.println(nome + " foi o " + colocacao + 
							"º colocado com " + percorrido + " movimentações");
	}
	/** Método run da thread Corrida de Carros */
	public void run () {
		while (distanciaCorrida < distanciaTotalCorrida) {
			carroAnda();
			carroImprimindoSituacao();
			numero = random.nextInt(100);
			
			if (numero > 95) {
				try {
					pitStop();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		colocacaoCarro();
	}
	
	public synchronized void pitStop() throws InterruptedException {
		System.out.println("\n[ENTRADA PIT STOP] " + nome + " ENTROU no pit stop! ");
		Thread.sleep(300);
		System.out.println("[SAIDA PIT STOP] " + nome + " SAIU do pit stop! ");
	}

}
