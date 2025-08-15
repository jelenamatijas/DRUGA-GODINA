class NFA:
    def __init__(self, start_state, accept_states, transitions=None):
        self.start_state = start_state
        self.accept_states = accept_states if isinstance(accept_states, set) else {accept_states}
        self.transitions = transitions or {}
    
    def add_transition(self, from_state, symbol, to_state):
        key = (from_state, symbol)
        if key not in self.transitions:
            self.transitions[key] = set()
        self.transitions[key].add(to_state)
    
    def add_epsilon_transition(self, from_state, to_state):
        self.add_transition(from_state, 'ε', to_state)
    
    def epsilon_closure(self, states):
        """
        Compute the epsilon closure of a set of states.
        Returns all states reachable from the given states by following epsilon transitions.
        """
        stack = list(states)
        closure = set(states)

        while stack:
            current = stack.pop()
            if (current, 'ε') in self.transitions:
                for nxt in self.transitions[(current, 'ε')]:
                    if nxt not in closure:
                        closure.add(nxt)
                        stack.append(nxt)
        return closure
    
    def run_enfa(self, input_string):
        """
        Run the epsilon-NFA on the given input string.
        Returns True if the input string is accepted, False otherwise.
        """
        current_states = self.epsilon_closure({self.start_state})

        for symbol in input_string:
            next_states = set()
            for state in current_states:
                if (state, symbol) in self.transitions:
                    next_states |= self.transitions[(state, symbol)]
            current_states = self.epsilon_closure(next_states)

        return len(current_states.intersection(self.accept_states)) > 0
    
    def match(self, input_string):
        """Check if the input string is accepted by this NFA"""
        return self.run_enfa(input_string)
    
    def __str__(self):
        result = f"NFA with {len(self.transitions)} transitions:\n"
        result += f"Start state: {self.start_state}\n"
        result += f"Accept states: {self.accept_states}\n"
        result += "Transitions:\n"
        
        sorted_transitions = sorted(self.transitions.items(), 
                                   key=lambda x: (x[0][0], str(x[0][1])))

        for (from_state, symbol), to_states in sorted_transitions:
            to_states_str = ", ".join(str(s) for s in sorted(to_states))
            result += f"  ({from_state}, {symbol}) -> {{{to_states_str}}}\n"
        
        return result