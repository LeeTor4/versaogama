package versaogama.conexao;

import java.sql.Connection;

public interface InterfacePool {
      Connection getConnection();
      void liberarConnection(Connection con);
}
