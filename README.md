# Úkol 2

Cílem je vytvořit webovou stránku, která při každém načtení zobrazí náhodně zvolený citát a náhodně zvolený obrázek. Stránka může vypadat nějak takhle
(v projektu jsou k tomu připravené styly), ale fantazii se meze nekladou:

![screenshot](screenshot.jpg)

1. Použij toto repository jako šablonu (Use this template), ze které si vytvoříš repository ve svém účtu na GitHubu.
1. Naklonuj si repository **ze svého účtu** na GitHubu na lokální počítač.
1. Spusť si naklonovanou aplikaci a otevři v prohlížeči stránku [http://localhost:8080/](http://localhost:8080/). Zobrazí se stránka s jedním citátem a
   obrázkem. Stránka je ovšem statická, při obnově stránky se nic nemění.
1. Uprav stránku tak, aby byla dynamická – aby se při každém načtení text i obrázek náhodně měnil.
1. Zkontroluj výsledek v prohlížeči.
1. *Commitni* a *pushnni* změny (výsledný kód) do svého repository na GitHubu.
1. Vlož odkaz na své repository jako řešení úkolu na portálu [Moje Czechitas](https://moje.czechitas.cz).

## Mohlo by se hodit

Jako zdroj obrázků může použít třeba [Unsplash](https://unsplash.com) – web s obrázky a fotografiemi [zdarma](https://unsplash.com/license). Obrázky z něj můžeš
stáhnout do své aplikace nebo je můžeš odkazovat přímo z jejich webu. Pokud chceš odkázat přímo na fotku s kódem `XXX` (kód je těch cca 10 znaků na konci URL
fotky), můžeš použít následující odkaz:

```
https://source.unsplash.com/XXX
https://source.unsplash.com/XXX/1600x900
```

Druhá varianta určuje požadované rozměry obrázku – pokud má obrázek jiný poměr stran, ořízne se na požadovaný poměr stran. Přesné rozměry obrazovky uživatele
ale neřeš – obrázek se pomocí CSS natáhne nebo smrští tak, aby pokryl celou stránku.

Jako zdroj citátů můžeš použít soubor `src/main/resources/citaty.txt`, který je součástí staženého projektu.

### Náhodná čísla

Pro generování náhodných čísel v Javě slouží třída [Random](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Random.html).

### Načtení textového souboru z *resourců*

Kód pro načtení řádků souboru v *resourcech* do seznamu řetězců (`List<String>`):

```java
private static List<String> readAllLines(String resource)throws IOException{
    //Soubory z resources se získávají pomocí classloaderu. Nejprve musíme získat aktuální classloader.
    ClassLoader classLoader=Thread.currentThread().getContextClassLoader();

    //Pomocí metody getResourceAsStream() získáme z classloaderu InpuStream, který čte z příslušného souboru.
    //Následně InputStream převedeme na BufferedRead, který čte text v kódování UTF-8 
    try(InputStream inputStream=classLoader.getResourceAsStream(resource);
        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream,StandardCharsets.UTF_8))){

    //Metoda lines() vrací stream řádků ze souboru. Pomocí kolektoru převedeme Stream<String> na List<String>.
    return reader
        .lines()
        .collect(Collectors.toList());
    }
}

// příklad volání: readAllLines("citaty.txt")
```

### Vytvoření seznamu textových řetězců přímo ve zdrojovém kódu Javy

Pokud chci vytvořit seznam několika textových řetězců a nechci je mít v externím souboru, můžu použít metodu `List.of()`:

```java
List<String> seznamTextu=List.of("řetězec 1","řetězec 2","další řetězec","ještě jiný řetězec");
```

### Obrázek na pozadí v inline stylu

Kaskádové styly (CSS) je možné psát i ve formě *inline stylů* – příslušný CSS kód je uvedený hned u HTML elementu, ke kterému se vztahuje, konkrétně v atributu
`style`. Následující HTML kód tedy vytvoří `div`, který bude mít nastavenou červenou barvu pozadí.

```html

<div style="background-color: red">Tento text má červenou barvu pozadí.</div>
```

### Vložení HTML místo obyčejného textu přes Freemarker

Když se ve Freemarkeru použije interpolace `${text}`, vloží se obsah proměnné `text` do stránky tak, aby bylo v prrohlížeči zobrazeno přesně, to, co je v textu.
Tzn. pokud bude obsahovat nějaké HTML značky, zobrazí se tyto značky na stránce textově, např. `<div>`.
Pokud chceš vložit text jako HTML, je potřeba použít `${text?no_esc}` (tzn. nemá se provádět escapování, tj. nahrazování znaků se speciálním významem).
**Pozor, takto vložený kód se nijak nekontroluje.
Je tedy potřeba takto vkládat pouze HTML kód, kterému důvěřuješ.
V žádném případě takto nelze vkládat kód, který pochází od uživatele.
Uživatel by totiž mohl do kódu podstrčit třeba značku `<script>` a spustit v prohlížeči jakýkoli svůj kód.**

### Odkazy

* odkaz na stránku [Lekce 2](https://java.czechitas.cz/2024-jaro/java-2-online/lekce-2.html)
* Java SE 21 [Javadoc](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/) – dokumentace všech tříd, které jsou součástí základní Javy ve verzi 21.
* Dokumentace [Spring Boot](https://spring.io/projects/spring-boot#learn) – odsud je anotace `@SpringBootApplication` a třída `SpringApplication`.
* Dokumentace [Spring Framework](https://spring.io/projects/spring-framework#learn) – odsud jsou anotace `@Controller`, `@GetRequest` a třída `ModelAndView`.
* Dokumentace [Freemarker](https://freemarker.apache.org/docs/index.html) – šablonovací systém pro HTML použitý v projektu.
* [Unsplash](https://unsplash.com) – obrázky a fotografie k použití zdarma
