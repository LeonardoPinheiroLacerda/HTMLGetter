package util.get_html;

public class Main {

	
	//Teste antes de exportar remover
	public static void main(String[] args) {
		String url = "http://www.loterias.caixa.gov.br/wps/portal/loterias/landing/duplasena/!ut/p/a1/04_Sj9CPykssy0xPLMnMz0vMAfGjzOLNDH0MPAzcDbwMPI0sDBxNXAOMwrzCjA2cDIAKIoEKnN0dPUzMfQwMDEwsjAw8XZw8XMwtfQ0MPM2I02-AAzgaENIfrh-FqsQ9wNnUwNHfxcnSwBgIDUyhCvA5EawAjxsKckMjDDI9FQGgnyPS/dl5/d5/L2dBISEvZ0FBIS9nQSEh/pw/Z7_HGK818G0K85260Q5OIRSC420O6/res/id=historicoHTML/c=cacheLevelPage/=/";
		
		Get get = new Get();
		get.setHtml(url);
		
		TagGetter getter = new TagGetter(get);
		
		System.out.println(getter.getGetter().getHtml());

	}

}
