package com.ndx.ndxvalidate.business.service;

import com.ndx.ndxvalidate.data.NdxMode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NdxModeService {

  public  List<NdxMode> listModes(){
        List<NdxMode> ndxModes = new ArrayList<>();
        NdxMode mode0 = new NdxMode("New Customer New Doctor", 0);
        NdxMode mode1 = new NdxMode("New Customer Old Doctor", 1);
        NdxMode mode2 = new NdxMode("Old Customer new Doctor", 2);
        ndxModes.add(mode0);
        ndxModes.add(mode1);
        ndxModes.add(mode2);

        return ndxModes;

    }
}
