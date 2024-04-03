package com.cake.cmodels.converter_tool.source.readers;

import com.cake.cmodels.converter_tool.source.Submodel;
import com.cake.cmodels.converter_tool.source.SubmodelReader;
import com.cake.cmodels.converter_tool.source.exception.SourceCompileError;
import com.cake.cmodels.core.CakeModel;
import com.cake.cmodels.core.model.Vertex;
import com.cake.cmodels.core.model.VertexNormal;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjModelReader implements SubmodelReader {
    
    @Override
    public Submodel readSource(JSONObject source, File file) throws SourceCompileError {
        try (
            BufferedReader fileInputStream = new BufferedReader(new FileReader(file.getAbsoluteFile()));
        ) {
            ArrayList<Vertex> readVertexes = new ArrayList<>();
            ArrayList<VertexNormal> vertexNormals = new ArrayList<>();
            //Read obj ref but I think this goes
            //Face { faces[Vertexindex, Vertexuv, Vertexnormal]}
            ArrayList<List<List<Integer>>> faceComponents = new ArrayList<>();
            
            fileInputStream.lines().forEach(
                line -> readSourceLine(line, readVertexes, vertexNormals, faceComponents)
            );
        } catch (IOException e) {
            throw new SourceCompileError("Unable to read file", e.getMessage());
        }
    
    
        return new CakeModel();
    }
    
    @Override
    public String getDefaultExtension() {
        return "obj";
    }
    
}
