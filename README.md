# MiniHaskell

```
Exp:: Int
  | Bool
  | Add Exp Exp
  | Sub Exp Exp
  | Mul Exp Exp
  | Div Exp Exp
  | And Exp Exp
  | Or Exp Exp
  | Not Exp
  | If Exp Then Exp Else Exp
  | Lambda \x -> Exp
  | Call Exp Exp...
  | Let x Exp Exp
```

## Projeto

1. [x] Expressões pendentes
2. [x] Suporte de multiplos parâmetros com seus respectivos tipos
3. [x] Declaração de Funções Nomeadas
4. [x] Implementar Expressão `If Then Else`
5. [x] Corrigir os erros de compilação dos testes disponibilizados.
6. Checagem de tipos

## Sugestão

```
Class Gama {
	HashMap ( variavel => tipo )
}
```

## TO DO

**Expressões pendentes**
+ [x] AND
+ [x] OR
+ [x] NOT
+ [x] MULTIPLICAÇAO
+ [x] SUBTRACAO
+ [x] DIVISÃO

**Parâmetro**
Uma classe que tem:
+ nome
+ tipo

**Declaração de Função**
Uma classe que tem:
+ nome: String
+ **lista de parâmetros**: Lista de Símbolos
+ corpo: Expressão

**Aplicação de Função Nomeada**

A aplicação de função nomeada e de uma expressão lambda serão representadas
da mesma forma na AST e na gramatica

**Verificação de Tipos**

**Tentar melhorar o Código**
+ [x] implementação de Stack mais atualizada
+ [x] nome dos funções
