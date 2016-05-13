package Tools;

import java.util.Comparator;

import model.FuncionarioHabilitado;

public class FuncionarioComparator implements Comparator<FuncionarioHabilitado>{
	private static FuncionarioComparator funcionarioComparatorInstance;
	
	public FuncionarioComparator getInstance() {
		if (FuncionarioComparator.funcionarioComparatorInstance == null) {
			FuncionarioComparator.funcionarioComparatorInstance = new FuncionarioComparator();
		}
		
		return FuncionarioComparator.funcionarioComparatorInstance;
	}
	
	@Override
	//funcionario1 < funcionario2 => funcionario1 tirará serviço antes do funcionario2
	public int compare(FuncionarioHabilitado o1, FuncionarioHabilitado o2) {
		if (o1.getQtdeServicosTirados() < o2.getQtdeServicosTirados()) {
			return -1;
		}
		else if (o1.getQtdeServicosTirados() > o2.getQtdeServicosTirados()) {
			return 1;
		}
		else {
			if (o1.getClassificacao() < o2.getClassificacao()) {
				return -1;
			}
			else if (o1.getClassificacao() > o2.getClassificacao()) {
				return 1;
			}
			else {
				return 0;
			}
		}
	}
}