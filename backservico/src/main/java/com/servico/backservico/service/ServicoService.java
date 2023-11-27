package com.servico.backservico.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

public class ServicoService {

    @Autowired
    private ServicoRepository ServicoRepository;
    
    public List<Servico> buscarTodos(){
        return ServicoRepository.findAll();

    }

    public List<Servico>buscarServicoPagamentoPendente(){
        return ServicoRepository.buscarServicoPagamentoPendente();
    }

    public List<Servico> buscarServicosCancelados(){
        return ServicoRepository.buscarServicosCancelados();
    }

    public Servico inserir (Servico servico){
        //return ServicoRepository.save(servico);
        if(servico.getValorPago()==null || servico.getValorPago()==0 || servico.getDataPagamento()==null){
            servico.setStatus("pendente");
        }else {
            servico.setStatus("realizado")
        }
        
        Servico servicoBanco = ServicoRepository.save(servico);
        return servicoBanco;


     }

     public servico alterar(Servico servico){
        if(servico.getValorPago()!=null && servico.getValorPago()==0 && servico.getDataPagamento()!=null){
            servico.setStatus("realizado");
        }
        return ServicoRepository.saveAndFlush(servico);

     }

        public void excluir (Long id){
            Servico servico = ServicoRepository.findById(id).get();
            ServicoRepository.delete(servico);
        }

}
