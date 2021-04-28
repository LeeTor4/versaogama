package versaogama.model.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopiaArquivoXML_DE_PARA {

	public boolean copia( File srcDir, File dstDir ){
	    
        try{

            if( srcDir.isDirectory() ){

                if( !dstDir.exists() ){

                    dstDir.mkdir();
                }

                String[] children = srcDir.list();

                for (int i=0; i<children.length; i++){

                    copia( new File( srcDir, children[i] ), new File( dstDir, children[i] ) );
                }
            } 
            else{

                InputStream in = new FileInputStream( srcDir );
                OutputStream out = new FileOutputStream( dstDir );

                byte[] buf = new byte[1024];
                int len;

                while( (len = in.read( buf ) ) > 0 ) {

                    out.write( buf, 0, len );
                }

                in.close();
                out.close();
            }
        }
        catch( IOException ioex ){

            ioex.printStackTrace();
            return false;
        }

        return true;
    }
}
