package org.springframework.samples.petclinic.user.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRender<T> {
    private String url;
    private Page<T> page;
    private int totalPaginas;
    private int numeroElemPag;
    private int pagActual;
    private List<Paginacion> paginas;

    public PageRender(String url, Page<T> page){
        this.url = url;
        this.page = page;
        this.paginas = new ArrayList<Paginacion>();

        numeroElemPag = 10;
        totalPaginas = page.getTotalPages();
        pagActual = page.getNumber()+1;

        int desde, hasta;
        if(totalPaginas <= numeroElemPag){
            desde = 1;
            hasta = totalPaginas;
        }else{
            if(pagActual<=numeroElemPag/2){
                desde = 1;
                hasta = numeroElemPag;
            }else if(pagActual>=totalPaginas - numeroElemPag/2){
                desde = totalPaginas-numeroElemPag+1;
                hasta = numeroElemPag;

            }else{
                desde = pagActual-numeroElemPag/2;
                hasta = numeroElemPag;
            }
        }
        for(int i = 0 ; i<hasta; i++){
            paginas.add(new Paginacion(desde+1, pagActual == desde+i));
        }
    }

    public boolean isLast(){
        return page.isLast();
    }

    public boolean isHasNext(){
        return page.hasNext();
    }

    public boolean isHasPrevius(){
        return page.hasPrevious();
    }

}
