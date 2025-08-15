from regex_parser_nodes import *
from enfa import NFA

class RegexToNFACompiler:
    def __init__(self):
        self.next_state_id = 0
    
    def compile(self, node):
        """Recursively compile a node in the parse tree to an NFA"""
        if isinstance(node, AlternationNode):
            return self._compile_alternation(node)
        
        elif isinstance(node, ConcatenationNode):
            return self._compile_concatenation(node)
        
        elif isinstance(node, RepetitionNode):
            return self._compile_repetition(node)
        
        elif isinstance(node, CharacterNode):
            return self._compile_char(node)
        
        else:
            raise ValueError(f"Unknown node type: {type(node)}")
    
    def _new_state(self):
        """Create a new state ID and increment the counter"""
        state_id = self.next_state_id
        self.next_state_id += 1
        return state_id
    
    def _compile_char(self, node):
        """Compile a character node into an NFA"""
        start = self._new_state()
        accept = self._new_state()
        
        # Create an NFA that accepts the single character
        nfa = NFA(start, accept)
        
        nfa.add_transition(start, node.value, accept)
        
        return nfa
    
    def _compile_alternation(self, node):
        """Compile an alternation (a|b) node into an NFA"""
        start = self._new_state()
        accept = self._new_state()
        
        # Create a new NFA that joins all the alternatives with epsilon transitions
        nfa = NFA(start, accept)
        
        # Handle each alternative (every other child, starting with the first)
        for i in range(0, len(node.children)):
            sub_nfa = self.compile(node.children[i])
            
            # Connect the start state to the sub-NFA's start state
            nfa.add_epsilon_transition(start, sub_nfa.start_state)
            
            # Connect the sub-NFA's accept states to the final accept state
            for sub_accept in sub_nfa.accept_states:
                nfa.add_epsilon_transition(sub_accept, accept)
            
            # Add all the sub-NFA's transitions
            nfa.transitions.update(sub_nfa.transitions)
        
        return nfa
    
    def _compile_concatenation(self, node):
        """Compile a concatenation (ab) node into an NFA"""
        # Compile the first part
        nfa = self.compile(node.children[0])
        
        # For each subsequent part in the concatenation
        for i in range(1, len(node.children)):
            next_nfa = self.compile(node.children[i])
            
            # Add all transitions from the next NFA
            nfa.transitions.update(next_nfa.transitions)
            
            # Connect each accept state from the first part to the start state of the next part
            for accept_state in nfa.accept_states:
                nfa.add_epsilon_transition(accept_state, next_nfa.start_state)
            
            # Update the accept states to be only those from the last part
            nfa.accept_states = next_nfa.accept_states

        return nfa
    
    def _compile_repetition(self, node):
        """Compile a repetition (a*) node into an NFA"""
        operator = node.children[1].value
        sub_nfa = self.compile(node.children[0])
        
        start = self._new_state()
        accept = self._new_state()
        
        nfa = NFA(start, accept)
        
        # Add all transitions from the sub-NFA
        nfa.transitions.update(sub_nfa.transitions)
        
        if operator == '*':  # zero or more
            # Can skip the sub-expression entirely
            nfa.add_epsilon_transition(start, accept)
            
            # Can enter the sub-expression
            nfa.add_epsilon_transition(start, sub_nfa.start_state)
            
            # Can repeat after completing the sub-expression
            for sub_accept in sub_nfa.accept_states:
                nfa.add_epsilon_transition(sub_accept, sub_nfa.start_state)
                nfa.add_epsilon_transition(sub_accept, accept)

        return nfa