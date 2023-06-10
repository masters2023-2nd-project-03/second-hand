import { createGlobalStyle } from 'styled-components';

const GlobalStyles = createGlobalStyle`
    *{
        margin: 0;
        padding:0;
        box-sizing: border-box;
    }

    button{
        background: inherit ; 
        border:none; 
        cursor:pointer
    }
`;

export default GlobalStyles;
