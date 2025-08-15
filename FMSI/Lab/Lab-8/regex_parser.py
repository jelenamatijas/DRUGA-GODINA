from regex_lexer import RegexLexer, Token, RegexLexerError
from regex_parser_nodes import *

class RegexParser:
    def __init__(self, lexer: RegexLexer):
        self.__parse_tree_builder = self.__ParseTreeBuilder(lexer)

    def build_parse_tree(self):
        return self.__parse_tree_builder.regex()

    class __ParseTreeBuilder:
        def __init__(self, lexer) -> None:
            self.lexer: RegexLexer = lexer
            self.token: Token = self.lexer.next()
        
        @property
        def pos(self):
            return self.lexer.source_position
        
        @property
        def token_type(self):
            return self.token.token_type
        
        def __consume(self, expected_token_type: str = None):
            if expected_token_type is not None and self.token_type != expected_token_type:
                raise RegexParserError(f'Expected {expected_token_type} but got {self.token_type}', self.pos)
            terminal_node = TerminalNode(self.token.token_type, self.token.value, self.pos)
            self.token = self.lexer.next()
            return terminal_node
        
        # GRAMMAR RULES for Regular Expressions
        # -------------------------------------

        # regex -> alternation
        def regex(self):
            return self.alternation()
        
        # alternation -> concatenation { "|" concatenation } *
        def alternation(self):
            children = [self.concatenation()]
            
            if self.token_type != '|':
                return children[0]  # No alternation, return the concatenation directly
                
            while self.token_type == '|':
                self.__consume('|')
                children += [self.concatenation()]
            
            return AlternationNode(children)
        
        # concatenation -> repetition { repetition } *
        def concatenation(self):
            children = [self.repetition()]
            
            # Concatenation happens when we have another term without an operator
            while self.token_type in ['CHAR', '(']:
                children.append(self.repetition())
            
            if len(children) == 1:
                return children[0]  # No concatenation, return the repetition directly
            
            return ConcatenationNode(children)
        
        # repetition -> group { "*" } *
        def repetition(self):
            group_node = self.group()
            
            if self.token_type in ['*']:
                operator = self.__consume('*')
                while self.token_type == '*':
                    self.__consume('*')
                return RepetitionNode([group_node, operator])
            
            return group_node
        
        # group -> "(" alternation ")" | character
        def group(self):
            if self.token_type == '(':
                self.__consume('('),
                subexpr = self.alternation()
                self.__consume(')')
                return subexpr
            else:
                return self.character()
        
        # character -> "CHAR"
        def character(self):
            char_token = self.__consume('CHAR')
            return CharacterNode(char_token.token_type, char_token.value, char_token.source_position)

class RegexParserError(ValueError):
    def __init__(self, message: str, position: int):
        self.message: str = message
        self.position: int = position