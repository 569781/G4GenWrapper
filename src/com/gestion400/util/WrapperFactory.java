package com.gestion400.util;

import java.io.*;

import com.gswrapper.control.*;
import com.gswrapper.modelo.wrapper.*;

public class WrapperFactory {
	
	public enum TipoWrapper{
		
		BASICO(WrapperX3270.class) {

			public Wrapper3270 newInstance() throws IOException {
				
				return new WrapperX3270(getPathEmulador());
			}
		},
		
		GSA(GSAWrapperX3270.class) {

			public Wrapper3270 newInstance() throws IOException {
				
				return new GSAWrapperX3270(getPathEmulador());
			}
		};
		
		private TipoWrapper( Class<? extends Wrapper3270> clase) {
			
			this.clase = clase;
		}
		
		private Class<? extends Wrapper3270> clase;
		
		public Class<? extends Wrapper3270> clase(){
			
			return clase;
		}
		
		
		public String getPathEmulador() {
			
			return getClass().getClassLoader().getResource(ConstantesAplicacion.PATH_EMULADOR).getPath();
		}
		
		public abstract Wrapper3270 newInstance() throws IOException;
		
	}
	
	public static Wrapper3270 getWrapper(TipoWrapper tipoWrapper) throws IOException {
		
		return tipoWrapper.newInstance();
	}
}
