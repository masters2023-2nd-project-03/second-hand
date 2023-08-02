import * as S from './styles';

import iconfiles from '../../assets/icons/index';
import Icon from '../Icon';

type IconName = keyof typeof iconfiles;

interface Tab {
  id: number;
  name: IconName;
  label: string;
  path: string;
}

const TABS: Tab[] = [
  { id: 0, name: 'home', label: '홈화면', path: '/' },
  { id: 1, name: 'newspaper', label: '판매내역', path: '/sales' },
  { id: 2, name: 'heart', label: '관심목록', path: '/heart' },
  { id: 3, name: 'message', label: '채팅', path: '/chat' },
  { id: 4, name: 'person', label: '내 계정', path: '/account' },
];

const TabBar = () => {
  return (
    <S.TabBarContainer>
      <S.TabBarMenu>
        <S.TabsList>
          {TABS.map((tab: Tab) => (
            <li key={tab.id}>
              <S.NavigationLink to={tab.path}>
                <Icon name={tab.name} width={'48'} height={'26'} />
                <div>{tab.label}</div>
              </S.NavigationLink>
            </li>
          ))}
        </S.TabsList>
      </S.TabBarMenu>
    </S.TabBarContainer>
  );
};

export default TabBar;
