public class StreamException{

public static void main(String[] args) {
		String input = "60001001,60001002,60001X03,60001004";
		//String input = "60001001,,60001003";
		OrFilter orFilter = new OrFilter();
		Arrays.stream(input.split(","))
		  .map(String::trim).map(x->toInteger(x)).filter(obj -> obj.isPresent()).flatMap(Optional::stream).forEach(val->System.out.println(val));
		  }		
	


			private static Optional<Integer> toInteger(String x) {
				try {
					return Optional.of(Integer.parseInt(x));
				} catch (Exception e) {
					System.out.println("Can not parse value :"+x);;
					return Optional.empty();
				}

			}
	}
