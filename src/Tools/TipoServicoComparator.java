package Tools;

import java.util.Comparator;

import model.TipoServico;

public class TipoServicoComparator implements Comparator<TipoServico> {

	@Override
	public int compare(TipoServico o1, TipoServico o2) {
		if (o1.getCor().equals(o2.getCor())) {
			return o1.getFuncao().compareTo(o2.getFuncao());
		}
		else {
			return o1.getCor().compareTo(o2.getCor());
		}
	}

}
