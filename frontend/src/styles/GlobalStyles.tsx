import { createGlobalStyle } from 'styled-components';

const GlobalStyles = createGlobalStyle`
    *{
        margin: 0;
        padding:0;
        box-sizing: border-box;
        text-decoration:none;
        color:inherit;
        list-style-type: none;
    }

    button{
        background: inherit ; 
        border:none; 
        cursor:pointer
    }

    input {
        border: none;
        outline: none;
    }

`;

export default GlobalStyles;
