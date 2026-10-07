class Token:
    def __init__(self, token_type: str, value: str):
        self.token_type: str = token_type
        self.value: str = value

class RegexLexer:
    def __init__(self, input: str):
        self.source: str = input
        self.source_position: int = 0

    def peek(self) -> Token:
        old_position: int = self.source_position
        token: Token = self.next()
        self.source_position = old_position
        return token

    def next(self) -> Token:
        while self.source_position < len(self.source) and self.source[self.source_position].isspace():
            self.source_position += 1

        if self.source_position == len(self.source):
            return Token("EOF", "EOF")
        elif self.source_position > len(self.source):
            raise Exception("Unexpected end of input")

        # Handle regex special characters
        if self.source[self.source_position] in ['(', ')', '*', '|']:
            special_char = self.source[self.source_position]
            self.source_position += 1
            return Token(special_char, special_char)
        
        # Handle escape sequences
        if self.source[self.source_position] == '\\':
            if self.source_position + 1 < len(self.source):
                self.source_position += 1
                escaped_char = self.source[self.source_position]
                self.source_position += 1
                return Token("CHAR", escaped_char)
            else:
                raise RegexLexerError("Incomplete escape sequence", self.source_position)
        
        # Handle normal characters
        char = self.source[self.source_position]
        self.source_position += 1
        return Token("CHAR", char)
    
class RegexLexerError(ValueError):
    def __init__(self, message: str, position: int):
        self.message: str = message
        self.position: int = position