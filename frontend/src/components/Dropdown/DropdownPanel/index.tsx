import * as S from './styles';

interface DropdownPanelProps {
  option: string;
  isMainLocation?: boolean;
  onClickNonOption?: () => Promise<void> | undefined;
  onClickOption?: () => void | undefined;
  isLastPanel?: boolean;
}

const DropdownPanel = ({
  option,
  isMainLocation,
  onClickNonOption,
  onClickOption,
  isLastPanel,
}: DropdownPanelProps) => {
  const handleClick = () => {
    if (onClickNonOption) {
      onClickNonOption();
    } else if (onClickOption) {
      onClickOption();
    }
  };

  return (
    <S.DropdownPanel onClick={handleClick} isLastPanel={isLastPanel}>
      <S.OptionTitle isLastPanel={isLastPanel} isMainLocation={isMainLocation}>
        {option}
      </S.OptionTitle>
    </S.DropdownPanel>
  );
};

export default DropdownPanel;
