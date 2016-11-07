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

## Programa para teste1
#### Programa 1
```bash
SE T1 VA-PARA 2 SENAO-VA-PARA 3
FACA F VA-PARA 6
SE T2 VA-PARA 5 SENAO-VA-PARA 4
FACA G VA-PARA 7
FACA F VA-PARA 7
SE T3 VA-PARA 4 SENAO-VA-PARA 1
```

#### Programa 2
```bash
FACA F VA-PARA 2
SE T1 VA-PARA 3 SENAO-VA-PARA 1
FACA G VA-PARA 4
SE T2 VA-PARA 1 SENAO-VA-PARA 5
FACA H VA-PARA 6
SE T3 VA-PARA 7 SENAO-VA-PARA 5
```


