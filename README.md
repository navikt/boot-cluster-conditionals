# boot-cluster-conditionals
Det kan ofte være nyttig å benytte forskjellige implementasjonsklasser i dev og prod, spesielt under utvikling av ny funksjonalitet, hvor man ønsker at applikasjonen skal være "deploybar", selv om funksjonaliteten ennå ikke er produksjonsklar. Den tradisjonelle løsningen på dette er antagelig feature toggles eller ad-hoc deteksjon av cluster/namespace.
Spring tilbyr mekanismer for  [Branch By Abstraction](https://martinfowler.com/bliki/BranchByAbstraction.html) gjennom annoteringer og dette biblioteket tilbyr noen utvidelser som gjør at man slipper kjøre slalom i koden basert på properties eller annet. 
Dette gjør det enklere å implementere [Conditional Bean Registration](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-condition-annotations
). Typisk bruk er :
~~~~

public interface Funksjonalitet {
  void doStuff();
}

@ConditionalOnNotProd
@Bean
public class NyFunksjonalitet implements Funksjonalitet {
  @Override
  void doStuff() {
     // ny logikk
  }
}

@ConditionalOnProd
@Bean
public class ProdFunksjonalitet implements Funksjonalitet {
  @Override
  void doStuff() {
     LOG.info("Doing stuff, but not really");
  }
}
~~~~
En bean som trenger en implementasjon av Funksjonalitet kan da få denne injisert på vanlig måte, i prod blir det ProdFunksjonalitet, i dev NyFunksjonalitet:
~~~~
@Bean
public class BeanSomTrengerFunksjonalitet {
   private final Funksjonalitet funksjonalitet;
   public BeanSomTrengerFunksjonalitet(Funksjonalitet funksjonalitet) {
       this.funksjonalitet = funksjonalitet;
   }
}
~~~~

