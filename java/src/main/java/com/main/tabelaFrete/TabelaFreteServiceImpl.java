package com.main.tabelaFrete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TabelaFreteServiceImpl implements TabelaFreteService {

    public static final String TABELA_FRETE_NAO_ENCONTRADA = "Tabela de Frete não encontrada com id: ";
    @Autowired
    private TabelaFreteRepository tabelaFreteRepository;
    @Override
    public TabelaFrete salvar(TabelaFrete tabelaFrete) {
        Double kmPercorridoDobrado = tabelaFrete.getKmPercorrido() * 2;
        if (tabelaFrete.getTaxaAdministracao() > kmPercorridoDobrado){
            throw new TabelaFreteException("A taxa de administração não pode representar mais que o dobro do valor do km percorrido");
        }
        return tabelaFreteRepository.save(tabelaFrete);
    }

    @Override
    public TabelaFrete buscarPorId(Integer id) {

        if (id < 0){
            throw new TabelaFreteException("ID deve ser maior que zero. " + id);
        }

        if (!tabelaFreteRepository.findById(id).isPresent()){
            throw new TabelaFreteException(TABELA_FRETE_NAO_ENCONTRADA + id);
        }

        return tabelaFreteRepository.findById(id).get();
    }

    @Override
    public void deletar(Integer id) {
        Optional<TabelaFrete> tabelaFrete = tabelaFreteRepository.findById(id);
        if (!tabelaFrete.isPresent()){
            throw new TabelaFreteException(TABELA_FRETE_NAO_ENCONTRADA + id);
        }
        tabelaFreteRepository.delete(tabelaFrete.get());
    }

    @Override
    public List<TabelaFrete> buscarPorDescricao(String descricao) {
        return tabelaFreteRepository.findByDescricaoCurtaContainingIgnoreCase(descricao);
    }

}
