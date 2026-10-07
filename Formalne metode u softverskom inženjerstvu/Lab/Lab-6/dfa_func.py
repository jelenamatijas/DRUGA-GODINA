def run_dfa(transition_function, start_state, accept_states, input_string):
    state = start_state
    for symbol in input_string:
        state = transition_function[(state, symbol)]
    return state in accept_states
    
if __name__ == "__main__": # Example DFA
    transition_function = {('A', '0'): 'B',
                           ('A', '1'): 'C',
                           ('B', '0'): 'A',
                           ('B', '1'): 'C',
                           ('C', '0'): 'C',
                           ('C', '1'): 'C'}
    input_string = '0101'
    print(f"Does the DFA accept the string '{input_string}'? " +
          f"{run_dfa(transition_function, start_state='A', accept_states={'C'}, input_string=input_string)}")