import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ICatalogs } from '../catalogs.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../catalogs.test-samples';

import { CatalogsService, RestCatalogs } from './catalogs.service';

const requireRestSample: RestCatalogs = {
  ...sampleWithRequiredData,
  createdTime: sampleWithRequiredData.createdTime?.toJSON(),
};

describe('Catalogs Service', () => {
  let service: CatalogsService;
  let httpMock: HttpTestingController;
  let expectedResult: ICatalogs | ICatalogs[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CatalogsService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find('ABC').subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a Catalogs', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const catalogs = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(catalogs).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Catalogs', () => {
      const catalogs = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(catalogs).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Catalogs', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Catalogs', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Catalogs', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCatalogsToCollectionIfMissing', () => {
      it('should add a Catalogs to an empty array', () => {
        const catalogs: ICatalogs = sampleWithRequiredData;
        expectedResult = service.addCatalogsToCollectionIfMissing([], catalogs);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(catalogs);
      });

      it('should not add a Catalogs to an array that contains it', () => {
        const catalogs: ICatalogs = sampleWithRequiredData;
        const catalogsCollection: ICatalogs[] = [
          {
            ...catalogs,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCatalogsToCollectionIfMissing(catalogsCollection, catalogs);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Catalogs to an array that doesn't contain it", () => {
        const catalogs: ICatalogs = sampleWithRequiredData;
        const catalogsCollection: ICatalogs[] = [sampleWithPartialData];
        expectedResult = service.addCatalogsToCollectionIfMissing(catalogsCollection, catalogs);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(catalogs);
      });

      it('should add only unique Catalogs to an array', () => {
        const catalogsArray: ICatalogs[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const catalogsCollection: ICatalogs[] = [sampleWithRequiredData];
        expectedResult = service.addCatalogsToCollectionIfMissing(catalogsCollection, ...catalogsArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const catalogs: ICatalogs = sampleWithRequiredData;
        const catalogs2: ICatalogs = sampleWithPartialData;
        expectedResult = service.addCatalogsToCollectionIfMissing([], catalogs, catalogs2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(catalogs);
        expect(expectedResult).toContain(catalogs2);
      });

      it('should accept null and undefined values', () => {
        const catalogs: ICatalogs = sampleWithRequiredData;
        expectedResult = service.addCatalogsToCollectionIfMissing([], null, catalogs, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(catalogs);
      });

      it('should return initial array if no Catalogs is added', () => {
        const catalogsCollection: ICatalogs[] = [sampleWithRequiredData];
        expectedResult = service.addCatalogsToCollectionIfMissing(catalogsCollection, undefined, null);
        expect(expectedResult).toEqual(catalogsCollection);
      });
    });

    describe('compareCatalogs', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCatalogs(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareCatalogs(entity1, entity2);
        const compareResult2 = service.compareCatalogs(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareCatalogs(entity1, entity2);
        const compareResult2 = service.compareCatalogs(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareCatalogs(entity1, entity2);
        const compareResult2 = service.compareCatalogs(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
