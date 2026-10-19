package Reparatodo.Repa.Bussines.Service.impl;

import Reparatodo.Repa.Bussines.Service.DispositivoService;
import Reparatodo.Repa.Bussines.dto.CrearDispositivoDTO;
import Reparatodo.Repa.Bussines.dto.DispositivoDTO;
import Reparatodo.Repa.Persistence.dao.DispositivoDao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class DispositivoServiceImpl implements DispositivoService {

    private final DispositivoDao dispositivoDao;


    @Override
    public DispositivoDTO crearDispositivo(CrearDispositivoDTO crearDispositivoDTO) {
        validarDatosDispositivo(crearDispositivoDTO);
        DispositivoDTO crearDispositivo= dispositivoDao.save(crearDispositivoDTO);
        return crearDispositivo;
    }

    private void validarDatosDispositivo(CrearDispositivoDTO crearDispositivoDTO) {
        if(crearDispositivoDTO.getCodigo()==null || crearDispositivoDTO.getCodigo()<=0){
            throw new IllegalArgumentException("El codigo es obligatorio");
        }
    }

    @Override
    @Transactional
    public DispositivoDTO getDispositivoById(Long id) {
        validarCodigo(id);
        return dispositivoDao.findById(id).orElseThrow(()->{
            return new RuntimeException("Codigo dispositivo no encontrado{}"+id);
        });
    }

    @Override
    public List<DispositivoDTO> getAll() {
        return dispositivoDao.lista();
    }

    private void validarCodigo(Long id) {
        if(id==null || id <=0){
    throw new IllegalArgumentException("Codigo invalido");
        }
    }
    
    
    
    
}
