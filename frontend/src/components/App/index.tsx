import { ThemeProvider } from 'styled-components';

import theme from '../../styles/theme';
import GlobalStyles from '../../styles/GlobalStyles';
import NavBarHome from '../NavBarHome';

const App = () => {
  return (
    <>
      <ThemeProvider theme={theme}>
        <GlobalStyles />
        <NavBarHome type="low" />
      </ThemeProvider>
    </>
  );
};

export default App;
