import { useState } from 'react';

import DropdownPanel from './dropdownPanel';
import Icon from '../icon/icon';

import * as S from './styles';

interface DropdownProps {
  options: string[];
}

const Dropdown: React.FC<DropdownProps> = ({ options }) => {
  const [selectedOption, setSelectedOption] = useState(options[0]);
  const [isOpen, setIsOpen] = useState(false);

  const handleOptionClick = (option: string) => {
    setSelectedOption(option);
    setIsOpen(false);
  };

  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };

  // "내 동네 변경하기" 기능 추후 추가 필요
  const handleChangeOptionClick = () => {
    console.log('동네 변경 페이지로 넘어간다.');
    setIsOpen(false);
  };

  return (
    <S.dropdownContainer>
      <S.dropdownHeader onClick={toggleDropdown}>
        <S.selectedOption>{selectedOption}</S.selectedOption>
        <Icon name={'arrowDown'} width="17" />
      </S.dropdownHeader>
      {isOpen && (
        <S.panelContainer>
          {options.map((option, index) => (
            <DropdownPanel
              key={index}
              option={option}
              onClick={handleOptionClick}
            />
          ))}
          <DropdownPanel
            key={2}
            option={'내 동네 변경하기'}
            onClick={handleChangeOptionClick}
          />
        </S.panelContainer>
      )}
    </S.dropdownContainer>
  );
};

export default Dropdown;
