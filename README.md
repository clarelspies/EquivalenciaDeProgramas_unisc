# Equivalencia De Programas
Repositório de programa usado para verificar a equivalência forte de programas em formato monolíticos, trabalho realizado para a disciplina de Teoria da Computação na UNISC 2016/2.

1 - Importar como maven Project.  
2 - No Eclipse, exportar como runnable jar file e selecionar para incluir dependencias. (Netbeans não está exportando corretamente).  
Documentos necessários para realizar testes e o enunciado do trabalho estão em /docs.

## Imagem da aplicação
<img src="docs/tela.PNG" width="968">

## Instruções:
Para o funcionamento correto do programa, aceita-se entradas nos seguintes formatos para representar instruções de programas em formato monolítico.

* FACA G VA-PARA 5 (OPERAÇÕES)
* SE T1 VA-PARA 2 SENAO-VA-PARA 1 (TESTE)

No área de texto "Programa 1 - Monolítico" e "Programa 2 - Monolítico" espera-se que seja digitado as instruções dos programas para a verificação. No botão ao lado chamado "Arquivo" pode ser carregado um arquivo txt com os programas de entradas e em "docs/" há diversos exemplos de programa em formato de instruções monolíticas aceitas pela aplicação.
Nos arquivos de instruções e na área de texto devemos cuidar para que não sobre no início ou no final das intruções uma linha em branco/quebra de linha.

Para a nomenclatura de testes, espera-se que ele inicie com o a letra "T" e para as operações qualquer caractére diferente de "T".

## entradas no formato de um programa monolítico
#### Programa 1
```bash
Se T1 va-para 2 senao-va-para 3
FACA G VA-PARA 1
FACA F VA-PARA 4
Se T2 va-para 5 senao-va-para 6
FACA F VA-PARA 4
FACA G VA-PARA 7
se t3 VA-PARA 8 senao-va-para 9
FACA F va-para 10
se t4 VA-PARA 13 senao-va-para 7
se t5 VA-PARA 13 senao-va-para 11
FACA G va-para 11
```

#### Programa 2
```bash
Se T1 va-para 2 senao-va-para 3
FACA G VA-PARA 1
FACA F VA-PARA 4
Se T2 va-para 3 senao-va-para 5
FACA G VA-PARA 6
Se T3 va-para 7 senao-va-para 8
FACA F VA-PARA 9
FACA F VA-PARA 8
Se T4 va-para 10 senao-va-para 6
```

Quando clicado no botão "iniciar", se as intruções estiverem todas no formato aceito serão executados os seguintes passos:

### Passo 1 - Definição das Instruções Rotuladas Compostas
O primeiro passo é passar as instruções para o formato de instruções rotuladas compostas, neste formato independente se a instrução é teste ou operação, elas terão o mesmo formato.

#### Programa 1
```bash
1:(G,2)(F,3)
2:(G,2)(F,3)
3:(F,4)(G,5)
4:(F,4)(G,5)
5:(F,6)(CICLO,ω)
6:(PARADA,Σ)(G,7)
7:(G,7)(G,7)
ω:(CICLO,ω)(CICLO,ω)
```

#### Programa 2
```bash
8:(G,9)(F,10)
9:(G,9)(F,10)
10:(F,10)(G,11)
11:(F,12)(F,13)
12:(PARADA,Σ)(F,13)
13:(F,13)(F,13)
```

### Passo 2 - Definição da Cadeia de Conjuntos Finitos
Neste momento é definida a cadeia de conjuntos finitos, para verificarmos se algum rótulo ficou fora do limite do programa

#### Programa 1
```bash
A0:{Σ}
A1:{Σ,6}
A2:{Σ,6,5}
A3:{Σ,6,5,4,3}
A4:{Σ,6,5,4,3,2,1}
A5:{Σ,6,5,4,3,2,1}
```

#### Programa 2
```bash
A0:{Σ}
A1:{Σ,12}
A2:{Σ,12,11}
A3:{Σ,12,11,10}
A4:{Σ,12,11,10,9,8}
A5:{Σ,12,11,10,9,8}
```

### Passo 3 - Simplificação
Esta etapa é necessária apenas quando no passo anterior é verificado que 1 ou mais rótulos ficaram além do limite do programa, para resolver este problema, substitui-se as referências a estes rótulos pos ciclos infinitos.

#### Programa 1
```bash
1:(G,2)(F,3)
2:(G,2)(F,3)
3:(F,4)(G,5)
4:(F,4)(G,5)
5:(F,6)(CICLO,ω)
6:(PARADA,Σ)(CICLO,ω)
ω:(CICLO,ω)(CICLO,ω)
```

#### Programa 2
```bash
8:(G,9)(F,10)
9:(G,9)(F,10)
10:(F,10)(G,11)
11:(F,12)(CICLO,ω)
12:(PARADA,Σ)(CICLO,ω)
ω:(CICLO,ω)(CICLO,ω)
```

### Passo 4 - Verificação da Equivalência
Esta é a etapa final, onde os rótulos de cada programa são comparados com o outro programa no mesmo ponto de execução. Caso seja identificado por exemplo, no programa 1 a execução de um TESTE e no programa 2 a execução de um CICLO no mesmo ponto, entende-se que os programas não são equivalentes e finaliza-se o teste. Do contrário, testa todos os rótulos até a PARADA para confirmar a equivalência.

#### Resultado
```bash
Os programas são fortemente equivalentes!

B0:{(1,8)}
B1:{(2,9),(3,10)}
B2:{(4,10),(5,11)}
B3:{(6,12),(ω,ω)}
B4:{(Σ,Σ),(ω,ω)}
B5:Ø
```


# Código Fonte

O código fonte esta disponível em licença aberta para estudo e modificação desde que seja citado o link do projeto no Github e o autor nas referências.
O código está comentado e possui alguns testes automatizados para melhor entendimento da aplicação.
