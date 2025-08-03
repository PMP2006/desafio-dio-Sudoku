package br.com.dio.model;

import java.util.Collection;
import java.util.List;

import static br.com.dio.model.GameStatusEnum.COMPLETE;
import static br.com.dio.model.GameStatusEnum.INCOMPLETE;
import static br.com.dio.model.GameStatusEnum.NON_STARTED;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Board {

    private final List<List<Space>> espacos;

    public Board(List<List<Space>> espacos) {
        this.espacos = espacos;
    }

    public List<List<Space>> getEspacos() {
        return espacos;
    }

    public GameStatusEnum getStatus(){
        if (espacos.stream().flatMap(Collection::stream).noneMatch(s -> !s.isFixa() && nonNull(s.getAtual()))){
            return NON_STARTED;
        }

        return espacos.stream().flatMap(Collection::stream).anyMatch(s -> isNull(s.getAtual())) ? INCOMPLETE : COMPLETE;
    }

    public boolean hasErrors(){
        if(getStatus() == NON_STARTED){
            return false;
        }

        return espacos.stream().flatMap(Collection::stream)
                .anyMatch(s -> nonNull(s.getAtual()) && !s.getAtual().equals(s.getEsperado()));
    }

    public boolean changeValue(final int col, final int row, final int value){
        var space = espacos.get(col).get(row);
        if (space.isFixa()){
            return false;
        }

        space.setAtual(value);
        return true;
    }

    public boolean clearValue(final int col, final int row){
        var space = espacos.get(col).get(row);
        if (space.isFixa()){
            return false;
        }

        space.clearSpace();
        return true;
    }

    public void reset(){
        espacos.forEach(c -> c.forEach(Space::clearSpace));
    }

    public boolean gameIsFinished(){
        return !hasErrors() && getStatus().equals(COMPLETE);
    }

}
