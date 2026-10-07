import pytest
from regex_lexer import RegexLexer, RegexLexerError
from regex_parser import RegexParser, RegexParserError
from regex_to_nfa import RegexToNFACompiler
from enfa import NFA

class TestRegexLexer:
    def test_basic_lexing(self):
        """Test basic lexing of regex tokens"""
        lexer = RegexLexer("a|b*c")
        
        expected_tokens = [
            ("CHAR", "a"),
            ("|", "|"),
            ("CHAR", "b"),
            ("*", "*"),
            ("CHAR", "c"),
            ("EOF", "EOF")
        ]
        
        for expected_type, expected_value in expected_tokens:
            token = lexer.next()
            assert token.token_type == expected_type
            assert token.value == expected_value
    
    def test_escaped_characters(self):
        """Test lexing of escaped special characters"""
        lexer = RegexLexer(r"a\*b\(c\|\)")
        
        expected_tokens = [
            ("CHAR", "a"),
            ("CHAR", "*"),
            ("CHAR", "b"),
            ("CHAR", "("),
            ("CHAR", "c"),
            ("CHAR", "|"),
            ("CHAR", ")"),
            ("EOF", "EOF")
        ]
        
        for expected_type, expected_value in expected_tokens:
            token = lexer.next()
            assert token.token_type == expected_type
            assert token.value == expected_value
    
    def test_invalid_escape(self):
        """Test that an incomplete escape sequence raises an error"""
        lexer = RegexLexer("a\\")
        
        with pytest.raises(RegexLexerError) as excinfo:
            lexer.next()
            lexer.next()  # The error should be on the second token
        
        assert "Incomplete escape sequence" in excinfo.value.message

class TestRegexParser:
    def test_simple_regex_parsing(self):
        """Test parsing a simple regex expression"""
        lexer = RegexLexer("ab|c")
        parser = RegexParser(lexer)
        
        # Since we can't easily check the structure of the parse tree in a unit test,
        # we're just asserting that it parses without errors
        parse_tree = parser.build_parse_tree()
        assert parse_tree is not None
    
    def test_complex_regex_parsing(self):
        """Test parsing a more complex regex expression"""
        complex_regex = "a(b|c)*d*e"
        lexer = RegexLexer(complex_regex)
        parser = RegexParser(lexer)
        
        parse_tree = parser.build_parse_tree()
        assert parse_tree is not None
    
    def test_unbalanced_parentheses(self):
        """Test that unbalanced parentheses raise a parser error"""
        lexer = RegexLexer("a(b|c")
        parser = RegexParser(lexer)
        
        with pytest.raises(RegexParserError):
            parser.build_parse_tree()

class TestRegexToNFACompiler:
    def test_simple_char_nfa(self):
        """Test compiling a simple character to NFA"""
        lexer = RegexLexer("a")
        parser = RegexParser(lexer)
        compiler = RegexToNFACompiler()
        
        parse_tree = parser.build_parse_tree()
        nfa = compiler.compile(parse_tree)
        
        # Verify NFA structure
        assert len(nfa.transitions) == 1
        assert len(nfa.accept_states) == 1
        
        # Test matching
        assert nfa.match("a") == True
        assert nfa.match("b") == False
        assert nfa.match("aa") == False
    
    def test_alternation_nfa(self):
        """Test compiling an alternation expression (a|b) to NFA"""
        lexer = RegexLexer("a|b")
        parser = RegexParser(lexer)
        compiler = RegexToNFACompiler()
        
        parse_tree = parser.build_parse_tree()
        nfa = compiler.compile(parse_tree)
        
        # Test matching
        assert nfa.match("a") == True
        assert nfa.match("b") == True
        assert nfa.match("c") == False
        assert nfa.match("ab") == False
    
    def test_concatenation_nfa(self):
        """Test compiling a concatenation expression (ab) to NFA"""
        lexer = RegexLexer("ab")
        parser = RegexParser(lexer)
        compiler = RegexToNFACompiler()
        
        parse_tree = parser.build_parse_tree()
        nfa = compiler.compile(parse_tree)
        
        # Test matching
        assert nfa.match("ab") == True
        assert nfa.match("a") == False
        assert nfa.match("b") == False
        assert nfa.match("abc") == False
    
    def test_kleene_star_nfa(self):
        """Test compiling a Kleene star expression (a*) to NFA"""
        lexer = RegexLexer("a*")
        parser = RegexParser(lexer)
        compiler = RegexToNFACompiler()
        
        parse_tree = parser.build_parse_tree()
        nfa = compiler.compile(parse_tree)
        
        # Test matching
        assert nfa.match("") == True
        assert nfa.match("a") == True
        assert nfa.match("aa") == True
        assert nfa.match("aaa") == True
        assert nfa.match("b") == False
    
    def test_complex_regex_nfa(self):
        """Test compiling a complex regex expression to NFA"""
        complex_regex = "a(b|c)*d*"
        lexer = RegexLexer(complex_regex)
        parser = RegexParser(lexer)
        compiler = RegexToNFACompiler()
        
        parse_tree = parser.build_parse_tree()
        nfa = compiler.compile(parse_tree)
        
        # Test matching
        assert nfa.match("ad") == True
        assert nfa.match("abd") == True
        assert nfa.match("acd") == True
        assert nfa.match("abcbcd") == True
        assert nfa.match("add") == True
        assert nfa.match("a") == True
        assert nfa.match("ab") == True
        assert nfa.match("ac") == True
        assert nfa.match("abc") == True
        assert nfa.match("b") == False
        assert nfa.match("c") == False
        assert nfa.match("d") == False

class TestIntegration:
    """End-to-end tests for the regex compiler pipeline"""
    
    def test_regex_to_nfa_match(self):
        """Test the full pipeline from regex string to NFA matching"""
        test_cases = [
            # (regex, [strings that should match], [strings that should not match])
            ("a", ["a"], ["", "b", "aa"]),
            ("a*", ["", "a", "aa", "aaa"], ["b", "ab"]),
            ("a|b", ["a", "b"], ["", "ab", "c"]),
            ("ab", ["ab"], ["", "a", "b", "abc"]),
            ("(a|b)c", ["ac", "bc"], ["", "a", "b", "c", "abc"]),
            ("a(b|c)*", ["a", "ab", "ac", "abc", "acb", "abbc"], ["", "b", "c", "bc"]),
            ("a(b|c)*d", ["ad", "abd", "acd", "abcd"], ["", "a", "ab", "ac", "bc", "d"]),
        ]
        
        for regex, match_strings, non_match_strings in test_cases:
            # Compile the regex to NFA
            lexer = RegexLexer(regex)
            parser = RegexParser(lexer)
            compiler = RegexToNFACompiler()
            
            parse_tree = parser.build_parse_tree()
            nfa = compiler.compile(parse_tree)
            
            # Test strings that should match
            for s in match_strings:
                assert nfa.match(s) == True, f"Regex '{regex}' should match '{s}'"
            
            # Test strings that should not match
            for s in non_match_strings:
                assert nfa.match(s) == False, f"Regex '{regex}' should not match '{s}'"