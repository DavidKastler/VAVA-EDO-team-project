# DataLayer

---
# Praca s backendom
- do databazy sa pristupuje pomocou "sublayerov"
- tieto sublayery sluzia na to aby bola zachovana cistota
kodu a lahko sa pridavali dalsie funkcionality
- tieto sublayery su
  - **model**
    - tu su triedy ktore reprezentuju tabulky v databaze
    - **exeption**
      - tu su len vynimky ktore dokazu nastat pocas spracovania modelov
  - **schema** *(data transfer object)*
    - obsahuje typ tried ktore reprezentuju prijate data z frontendu
    - ide o typ komunikacie s frontendom napriklad pri prihlaseni nevieme roleId a preto
      posleme len username a password (UserLogin)
    - priklad: ked cheme pridat usera, tak rola je len id a
      v databaze je na neho uz namapovana rola, takze trieda "User"
      nedokaze prijat roleId a preto existuje trieda "UserRegister" ktora toto riesi
  - **repository**
    - tu su rozhrania ktore obsahuju metody na pracu s databazou
    - vacsinou su prazdne, no daju sa rozsirit o custom metody nad databazou
  - **service** *(CRUD triedy)*
    - tu su sluzby ktore pracuju s databazou, inde by sa nemalo robit s databazou
  - **controller**
    - tuto triedy obsahuju endpointy a vyuzivaju metody zo sluzieb


---
# First time setup

- potrebne veci na prve spustenie
  1. treba mat vytvorenu databazu v postrese cez sql alebo v **pgAdmine cez UI**
     - ```sql 
       CREATE DATABASE vava
       ```
  2. Treba mat tabulky v databaze z sql suboru v resources priecinku
     - ``src/main/resources/PostgreSQL-database-setup.sql``
  3. V priecinku application.properties ``src/main/resources/application.properties`` 
  treba zmenit prihlasovacie udaje do databazy
     - ```properties
        spring.datasource.url=jdbc:postgresql://localhost:5432/vava
        spring.datasource.username=TVOJ_USERNAME
        spring.datasource.password=TVOJE_HESLO
       ```

---
# Spustenie aplikacie
- aplikaciu spustame cez triedu **StartUpBackend** v priecinku ``src/main/java/vava/edo/StartUpBackend.java``

