class NFAVisualizer:
    def __init__(self):
        pass
        
    def visualize(self, nfa):
        """Print a visual representation of the NFA"""
        print(f"ε-NFA with {len(nfa.transitions)} transitions")
        print(f"Start state: {nfa.start_state}")
        print(f"Accept states: {', '.join(str(state) for state in nfa.accept_states)}")
        print("\nTransitions:")
        
        # Sort transitions for better readability
        sorted_transitions = sorted(nfa.transitions.items(), 
                                   key=lambda x: (x[0][0], str(x[0][1])))
        
        for (from_state, symbol), to_states in sorted_transitions:
            to_states_str = ", ".join(str(s) for s in sorted(to_states))
            print(f"  ({from_state}, {symbol}) -> {{{to_states_str}}}")
            
    def visualize_test(self, nfa, test_strings):
        """Test the NFA against multiple input strings and visualize the results"""
        print("\nTesting NFA against input strings:")
        for string in test_strings:
            accepted = nfa.match(string)
            print(f"  Input: {string!r:10} | Accepted: {accepted}")