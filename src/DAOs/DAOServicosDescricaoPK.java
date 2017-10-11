package DAOs;

import static DAOs.DAOGenerico.em;
import Entidades.ServicosDescricao;
import Entidades.ServicosDescricaoPK;
import java.util.ArrayList;
import java.util.List;

public class DAOServicosDescricaoPK extends DAOGenerico<ServicosDescricao> {

    public DAOServicosDescricaoPK() {
        super(ServicosDescricao.class);
    }

}