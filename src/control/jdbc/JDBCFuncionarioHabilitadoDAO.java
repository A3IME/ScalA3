package control.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import control.dao.FuncionarioHabilitadoDAO;
import model.FuncionarioHabilitado;
import model.TipoServico;

public class JDBCFuncionarioHabilitadoDAO extends JDBCDAO implements FuncionarioHabilitadoDAO {
	@Override
	public List<FuncionarioHabilitado> listarFuncionariosHabilitados(TipoServico tipoServico) {
		List<FuncionarioHabilitado> result = new ArrayList<FuncionarioHabilitado>();
		try {
			if (this.statement == null) {
				this.statement = this.database.createStatement();
			}
			
			/*this.resultSet = this.statement.executeQuery("SELECT idfunc, nomecompleto, classificacao, count(idservico) "
					+ "FROM (funcionario NATURAL LEFT JOIN "
					+ "(servico_funcionario NATURAL INNER JOIN servico)) "
					+ "WHERE idfunc != 0 "
					+ "AND eadmin = false "
					+ "AND habilitacao & " + (int)Math.pow(2, tipoServico.getId()) + " != 0 "
					+ "AND (idtiposervico = " + tipoServico.getId() + " OR idtiposervico is null) "
					+ "GROUP BY idfunc, idtiposervico;");*/
			System.out.println("///////////");
			System.out.println(tipoServico.getId());
			System.out.println("///////////");
			this.resultSet = this.statement.executeQuery("SELECT DISTINCT * FROM (funcionario NATURAL LEFT JOIN "
					+ "(SELECT idfunc, idtiposervico, count(idservico) "
					+ "FROM servico_funcionario NATURAL INNER JOIN servico WHERE idtiposervico = " + tipoServico.getId() + " GROUP BY idfunc, idtiposervico) AS temp) "
					+ "WHERE idfunc!=0 AND eadmin=false AND habilitacao&(1<<"+ tipoServico.getId() +") != 0;");	
			
						
			System.out.println("++++++++");
			System.out.println(tipoServico.getId());
			System.out.println("++++++++");
			while (this.resultSet.next()) {
				result.add(new FuncionarioHabilitado(this.resultSet.getInt("idfunc"), 
						this.resultSet.getString("nomecompleto"), 
						this.resultSet.getInt("classificacao"), 
						this.resultSet.getInt("count")));
				System.out.println("&&" + resultSet.getString("idfunc") + "-" + resultSet.getString("count"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
