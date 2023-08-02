import * as S from './styles';

import Icon from '../Icon';

interface SearchBarProps {
  searchTerm: string;
  setSearchTerm: (searchTerm: string) => void;
}

export const SearchBar = ({ searchTerm, setSearchTerm }: SearchBarProps) => {
  const handleInputLocation = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(event.target.value);
  };

  return (
    <S.SearchBarContainer>
      <S.IconWrapper>
        <Icon name="search" width="17" fill="#3C3C43" />
      </S.IconWrapper>
      <S.Input
        type="text"
        placeholder="동명(읍,면)으로 검색(ex. 역삼1동)"
        value={searchTerm}
        onChange={handleInputLocation}
      />
    </S.SearchBarContainer>
  );
};
