# -------------------------------------------------------------- #
# Abstract node classes for regex parse tree
# -------------------------------------------------------------- #

class NonterminalNode():
    '''
    A nonterminal node is a node that has children.
    It is an internal node in the parse tree.
    It represents a nonterminal symbol in the grammar.
    '''
    def __init__(self, *children):
        # 1) If a single list or tuple is passed, unpack it.
        if len(children) == 1 and isinstance(children[0], (list, tuple)):
            self.children = list(children[0])
        else: # 2) Otherwise, unpack the arguments.
            self.children = list(children)

class TerminalNode():
    '''
    A terminal node is a node that does not have any children.
    It is a leaf node in the parse tree.
    It represents a token with its semantic value.
    '''
    def __init__(self, token_type, value, source_position=None):
        self.token_type = token_type
        self.value = value
        self.source_position = source_position

# -------------------------------------------------------------- #
# Nonterminal nodes for Regular Expressions
# -------------------------------------------------------------- #

class AlternationNode(NonterminalNode):
    """Node for alternation (|) operation"""
    pass

class ConcatenationNode(NonterminalNode):
    """Node for concatenation operation"""
    pass

class RepetitionNode(NonterminalNode):
    """Node for repetition (*) operation"""
    pass

class CharacterNode(TerminalNode):
    """Node for a single character or character class"""
    pass